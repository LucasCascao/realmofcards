package br.edu.les.realmofcard.strategy.pedido;

import br.edu.les.realmofcard.strategy.IStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.les.realmofcard.domain.*;
import br.edu.les.realmofcard.dao.CartaDAO;
import br.edu.les.realmofcard.util.Util;

import java.util.ArrayList;
import java.util.List;

@Component
public class RetornaItemEstoque implements IStrategy {

    @Autowired
    private CartaDAO cartaDAO;

    @Override
    public String processar(EntidadeDominio entidade) {

        if(entidade instanceof Pedido || entidade instanceof Transicao){

            Pedido pedido = null;

            if(entidade instanceof Pedido)
                pedido = (Pedido) entidade;
            else if(entidade instanceof Transicao)
                pedido = ((Transicao) entidade).getPedido();

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
