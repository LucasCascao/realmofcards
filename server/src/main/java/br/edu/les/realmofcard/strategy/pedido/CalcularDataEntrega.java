package br.edu.les.realmofcard.strategy.pedido;

import br.edu.les.realmofcard.strategy.IStrategy;
import org.springframework.stereotype.Component;

import br.edu.les.realmofcard.domain.EntidadeDominio;
import br.edu.les.realmofcard.domain.Pedido;
import br.edu.les.realmofcard.util.Util;
import br.edu.les.realmofcard.util.correio.WebServiceCorreio;

import java.time.LocalDate;

@Component
public class CalcularDataEntrega implements IStrategy {

    @Override
    public String processar(EntidadeDominio entidade) {

        if(entidade instanceof Pedido){

            Pedido pedido = (Pedido) entidade;
            
            Integer diasParaEntrega = new WebServiceCorreio().calculaPrecoPrazo(pedido.getEndereco().getCep()).getQuantidadeDiasEntrega();
            
            if(Util.isNotNull(diasParaEntrega))
            	pedido.setDataEstimada(LocalDate.now().plusDays(diasParaEntrega));
        	else
        		pedido.setDataEstimada(LocalDate.now().plusDays(7));
        }

        return null;
    }
}
