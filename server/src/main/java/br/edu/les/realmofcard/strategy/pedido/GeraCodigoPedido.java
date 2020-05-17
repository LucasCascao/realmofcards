package br.edu.les.realmofcard.strategy.pedido;

import br.edu.les.realmofcard.strategy.IStrategy;
import org.springframework.stereotype.Component;

import br.edu.les.realmofcard.domain.EntidadeDominio;
import br.edu.les.realmofcard.domain.Pedido;
import br.edu.les.realmofcard.util.GeradorCodigo;
import br.edu.les.realmofcard.util.Util;

@Component
public class GeraCodigoPedido implements IStrategy {
    @Override
    public String processar(EntidadeDominio entidade) {

        if(entidade instanceof Pedido){
            Pedido pedido = (Pedido) entidade;

            if(Util.isNotNull(pedido)){
                pedido.setCodigoPedido(GeradorCodigo.gerarCodigoPedido());
            }
        }
        return null;
    }
}
