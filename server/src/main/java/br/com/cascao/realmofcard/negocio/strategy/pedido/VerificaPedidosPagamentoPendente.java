package br.com.cascao.realmofcard.negocio.strategy.pedido;

import br.com.cascao.realmofcard.domain.*;
import br.com.cascao.realmofcard.enumerator.StatusPedidoEnum;
import br.com.cascao.realmofcard.negocio.strategy.IStrategy;
import br.com.cascao.realmofcard.negocio.strategy.email.EnviaEmailPedidoPagamentoAprovado;
import br.com.cascao.realmofcard.negocio.strategy.email.EnviaEmailPedidoPagamentoRejeitado;
import br.com.cascao.realmofcard.persistence.PedidoPersistence;
import br.com.cascao.realmofcard.repository.CartaoCreditoRepository;
import br.com.cascao.realmofcard.repository.PedidoRepository;
import br.com.cascao.realmofcard.util.Util;
import br.com.cascao.realmofcard.util.validador.ValidadorString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
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
    private PedidoPersistence pedidoPersistence;

    @Override
    public String processar(EntidadeDominio entidade) {

        StringBuilder msg = new StringBuilder();
        
        List<String> numeroCartoesPermitidosParaCompra = new ArrayList<>();

    	numeroCartoesPermitidosParaCompra.add("9999");
    	numeroCartoesPermitidosParaCompra.add("7777");
    	numeroCartoesPermitidosParaCompra.add("5555");
    	numeroCartoesPermitidosParaCompra.add("1111");
    	numeroCartoesPermitidosParaCompra.add("3333");

        if(entidade instanceof Pedido){

        	Set<Pedido> pedidosPagamentoPendente = pedidoRepository
                    .findByStatusPedido_Id(Pedido.builder().statusPedido(StatusPedido.builder().id(1).build()).build().getStatusPedido().getId());

            for (Pedido pedido : pedidosPagamentoPendente) {

                Set<FormaPagamento> formaPagamentoList = pedido.getFormaPagamentoList();

                for (FormaPagamento formaPagamento : formaPagamentoList) {

                    Boolean naoEValido = false;

                    if(!numeroCartoesPermitidosParaCompra.contains(formaPagamento.getRegistroCartao())){
                        naoEValido = true;
                    }

                    if(naoEValido){
                        enviaEmailPedidoPagamentoRejeitado.processar(pedido);
                        pedido.setStatusPedido(StatusPedido.builder().id(2).build());
                        pedidoPersistence.alterar(pedido);
                    } else {
                        enviaEmailPedidoPagamentoAprovado.processar(pedido);
                        pedido.setStatusPedido(StatusPedido.builder().id(3).build());
                        pedidoPersistence.alterar(pedido);
                    }
                }
            }
        }

        return msg.toString();
    }
}
