package br.com.cascao.realmofcard.negocio.strategy.pedido;

import br.com.cascao.realmofcard.domain.*;
import br.com.cascao.realmofcard.negocio.strategy.IStrategy;
import br.com.cascao.realmofcard.negocio.strategy.email.EnviaEmailPedidoPagamentoAprovado;
import br.com.cascao.realmofcard.negocio.strategy.email.EnviaEmailPedidoPagamentoRejeitado;
import br.com.cascao.realmofcard.persistence.CartaPersistence;
import br.com.cascao.realmofcard.persistence.PedidoPersistence;
import br.com.cascao.realmofcard.repository.PedidoRepository;
import br.com.cascao.realmofcard.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class RetornaItemEstoque implements IStrategy {

    @Autowired
    private EnviaEmailPedidoPagamentoAprovado enviaEmailPedidoPagamentoAprovado;

    @Autowired
    private EnviaEmailPedidoPagamentoRejeitado enviaEmailPedidoPagamentoRejeitado;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoPersistence pedidoPersistence;

    @Autowired
    private CartaPersistence cartaPersistence;

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
                    cartaPersistence.alterar(cartaDevolvida);
                }

            }

        }

        return null;
    }
}
