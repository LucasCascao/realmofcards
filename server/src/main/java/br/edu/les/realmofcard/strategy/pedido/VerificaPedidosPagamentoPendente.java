package br.edu.les.realmofcard.strategy.pedido;

import br.edu.les.realmofcard.strategy.IStrategy;
import br.edu.les.realmofcard.strategy.email.pedido.EnviaEmailPedidoPagamentoAprovado;
import br.edu.les.realmofcard.strategy.email.pedido.EnviaEmailPedidoPagamentoRejeitado;
import br.edu.les.realmofcard.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.les.realmofcard.domain.*;
import br.edu.les.realmofcard.dao.CartaDAO;
import br.edu.les.realmofcard.dao.PedidoDAO;
import br.edu.les.realmofcard.repository.PedidoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class VerificaPedidosPagamentoPendente implements IStrategy {

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

        StringBuilder msg = new StringBuilder();
        
        List<String> numeroCartoesPermitidosParaCompra = new ArrayList<>();

    	numeroCartoesPermitidosParaCompra.add("9999999999999999");
    	numeroCartoesPermitidosParaCompra.add("7777777777777777");
    	numeroCartoesPermitidosParaCompra.add("5555555555555555");
    	numeroCartoesPermitidosParaCompra.add("1111111111111111");
    	numeroCartoesPermitidosParaCompra.add("3333333333333333");

        if(entidade instanceof Pedido){

        	Set<Pedido> pedidosPagamentoPendente = pedidoRepository
                    .findByStatusPedido_Id(Pedido.builder().statusPedido(StatusPedido.builder().id(1).build()).build().getStatusPedido().getId());

            for (Pedido pedido : pedidosPagamentoPendente) {

                Set<FormaPagamento> formaPagamentoList = pedido.getFormaPagamentoList();

                for (FormaPagamento formaPagamento : formaPagamentoList) {

                    Boolean naoEValido = false;

                    if(Util.isNotNull(formaPagamento.getCartaoCredito())
                        && !numeroCartoesPermitidosParaCompra.contains(formaPagamento.getCartaoCredito().getNumero())){
                        naoEValido = true;
                    }

                    if(naoEValido){
                        enviaEmailPedidoPagamentoRejeitado.processar(pedido);
                        pedido.setStatusPedido(StatusPedido.builder().id(2).build());
                        pedidoDAO.alterar(pedido);
                        for (Item item : pedido.getItemList()) {
                            Carta cartaDevolvida = item.getCarta();
                            cartaDevolvida.setQuantidadeDisponivel(cartaDevolvida.getQuantidadeDisponivel() + item.getQuantidade());
                            cartaDevolvida.setQuantidadeEstoque(cartaDevolvida.getQuantidadeEstoque() + item.getQuantidade());
                            if(cartaDevolvida.getStatus().getId().equals(2))
                                cartaDevolvida.setStatus(Status.builder().id(1).build());
                            cartaDAO.alterar(cartaDevolvida);
                        }
                    } else {
                        enviaEmailPedidoPagamentoAprovado.processar(pedido);
                        pedido.setStatusPedido(StatusPedido.builder().id(3).build());
                        pedidoDAO.alterar(pedido);
                    }
                }
            }
        }

        return msg.toString();
    }
}
