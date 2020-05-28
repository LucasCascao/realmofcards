package br.edu.les.realmofcard.strategy.pedido;

import br.edu.les.realmofcard.strategy.IStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.les.realmofcard.domain.EntidadeDominio;
import br.edu.les.realmofcard.domain.Pedido;
import br.edu.les.realmofcard.util.Util;
import br.edu.les.realmofcard.util.validador.ValidadorString;

@Component
public class ValidaDadosPedido implements IStrategy {

    @Autowired
    ValidadorString validadorString;

    @Override
    public String processar(EntidadeDominio entidade) {

        StringBuilder msg = new StringBuilder();

        if(entidade instanceof Pedido){

            Pedido pedido = (Pedido) entidade;

            msg.append(validadorString.validar(pedido.getCliente(), "cliente"));
            msg.append(validadorString.validar(pedido.getFormaPagamentoList(), "forma de pagamento"));
            msg.append(validadorString.validar(pedido.getEndereco(), "endereço"));
            msg.append(validadorString.validar(pedido.getItemList(), "itens"));
            msg.append(validadorString.validar(pedido.getStatusPedido(), "status do pedido"));
            msg.append(validadorString.validar(pedido.getValorTotal(), "valor total"));

        }

        return msg.toString();
    }
}
