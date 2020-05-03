package br.com.cascao.realmofcard.negocio.strategy.pedido;

import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.domain.Pedido;
import br.com.cascao.realmofcard.domain.Item;
import br.com.cascao.realmofcard.negocio.strategy.IStrategy;
import br.com.cascao.realmofcard.util.Util;
import br.com.cascao.realmofcard.util.correio.WebServiceCorreio;

import org.springframework.stereotype.Component;

@Component
public class CalculaValorPedido implements IStrategy {

    @Override
    public String processar(EntidadeDominio entidade) {

        if(entidade instanceof Pedido){

            Pedido pedido = (Pedido) entidade;
            
            Double custoFrete = new WebServiceCorreio().calculaPrecoPrazo(pedido.getEndereco().getCep()).getValorCusto();

            if(Util.isNotNull(pedido.getItemList())){

                Double valorTotal = 0.0;

                for (Item item: pedido.getItemList()) {
                    valorTotal += item.getCarta().getValorVenda() * item.getQuantidade();
                }
                
                if(Util.isNotNull(custoFrete))
                	valorTotal += custoFrete;

                pedido.setValorTotal(valorTotal);

            }

        }

        return null;
    }
}
