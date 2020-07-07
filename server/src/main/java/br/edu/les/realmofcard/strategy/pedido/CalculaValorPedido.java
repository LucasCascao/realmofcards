package br.edu.les.realmofcard.strategy.pedido;

import br.edu.les.realmofcard.strategy.IStrategy;
import org.springframework.stereotype.Component;

import br.edu.les.realmofcard.domain.EntidadeDominio;
import br.edu.les.realmofcard.domain.Item;
import br.edu.les.realmofcard.domain.Pedido;
import br.edu.les.realmofcard.util.Util;
import br.edu.les.realmofcard.util.correio.WebServiceCorreio;

@Component
public class CalculaValorPedido implements IStrategy {

    @Override
    public String processar(EntidadeDominio entidade) {

        if(entidade instanceof Pedido){

            Pedido pedido = (Pedido) entidade;
            
            Double custoFrete = new WebServiceCorreio().calculaPrecoPrazo(pedido.getEndereco().getCep()).getValorCusto();

            if(Util.isNotNull(pedido.getItemList())){

                Double valorTotal = 0.0;

                Double valorGrupoPrecificacao;

                for (Item item: pedido.getItemList()) {
                    valorGrupoPrecificacao = item.getCarta().getGrupoPrecificacao().getValor() / 100;
                    valorTotal += (item.getCarta().getValorCompra() + (item.getCarta().getValorCompra() * valorGrupoPrecificacao)) * item.getQuantidade();
                }
                
                if(Util.isNotNull(custoFrete))
                	valorTotal += custoFrete;

                pedido.setValorTotal(valorTotal);

            }

        }

        return null;
    }
}
