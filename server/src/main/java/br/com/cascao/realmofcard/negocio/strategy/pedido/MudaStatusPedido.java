package br.com.cascao.realmofcard.negocio.strategy.pedido;

import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.domain.Pedido;
import br.com.cascao.realmofcard.negocio.strategy.IStrategy;
import br.com.cascao.realmofcard.util.validador.ValidadorString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MudaStatusPedido implements IStrategy {

    @Autowired
    ValidadorString validadorString;

    @Override
    public String processar(EntidadeDominio entidade) {

        StringBuilder msg = new StringBuilder();

        if(entidade instanceof Pedido){

            Pedido pedido = (Pedido) entidade;

            msg.append(validadorString.validar(pedido.getCliente(), "cliente"));
            msg.append(validadorString.validar(pedido.getFormaPagamentoList(), "forma de pagamento"));
            msg.append(validadorString.validar(pedido.getEndereco(), "endereco"));
            msg.append(validadorString.validar(pedido.getItemList(), "itens"));
            msg.append(validadorString.validar(pedido.getStatusPedido(), "status do pedido"));
            msg.append(validadorString.validar(pedido.getValorTotal(), "valor total"));

        }

        return msg.toString();
    }
}
