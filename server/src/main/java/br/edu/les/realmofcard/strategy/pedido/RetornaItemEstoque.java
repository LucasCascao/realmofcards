package br.edu.les.realmofcard.strategy.pedido;

import br.edu.les.realmofcard.strategy.IStrategy;
import br.edu.les.realmofcard.strategy.email.EnviaEmailPedidoPagamentoAprovado;
import br.edu.les.realmofcard.strategy.email.EnviaEmailPedidoPagamentoRejeitado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.les.realmofcard.domain.*;
import br.edu.les.realmofcard.dao.CartaDAO;
import br.edu.les.realmofcard.dao.PedidoDAO;
import br.edu.les.realmofcard.repository.PedidoRepository;
import br.edu.les.realmofcard.util.Util;

import java.util.ArrayList;
import java.util.List;

@Component
public class RetornaItemEstoque implements IStrategy {

    @Autowired
    private EnviaEmailPedidoPagamentoAprovado enviaEmailPedidoPagamentoAprovado;

    @Autowired
    private EnviaEmailPedidoPagamentoRejeitado enviaEmailPedidoPagamentoRejeitado;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoDAO pedidoDAO;

    @Autowired
    private CartaDAO cartaDAO;

    @Override
    public String processar(EntidadeDominio entidade) {

        if(entidade instanceof Pedido || entidade instanceof Troca){

            Pedido pedido = null;

            if(entidade instanceof Pedido)
                pedido = (Pedido) entidade;
            else if(entidade instanceof Troca)
                pedido = ((Troca) entidade).getPedidoParaTroca();

            List<Integer> listaStatusPermitidos = new ArrayList<>();

            listaStatusPermitidos.add(2);
            listaStatusPermitidos.add(4);
            listaStatusPermitidos.add(10);

            if(Util.isNotNull(pedido)
                    && Util.isNotNull(pedido.getStatusPedido())
                    && Util.isNotNull(pedido.getStatusPedido().getId())
                    && listaStatusPermitidos.contains(pedido.getStatusPedido().getId())) {

                for (Item item : pedido.getItemList()) {
                    Carta cartaDevolvida = item.getCarta();
                    cartaDevolvida.setQuantidadeDisponivel(cartaDevolvida.getQuantidadeDisponivel() + item.getQuantidade());
                    cartaDevolvida.setQuantidadeEstoque(cartaDevolvida.getQuantidadeEstoque() + item.getQuantidade());
                    if(cartaDevolvida.getStatus().getId().equals(2))
                        cartaDevolvida.setStatus(Status.builder().id(1).build());
                    cartaDAO.alterar(cartaDevolvida);
                }

            }

        }

        return null;
    }
}
