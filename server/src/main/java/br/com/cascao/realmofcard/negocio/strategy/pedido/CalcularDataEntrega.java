package br.com.cascao.realmofcard.negocio.strategy.pedido;

import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.domain.Item;
import br.com.cascao.realmofcard.domain.Pedido;
import br.com.cascao.realmofcard.negocio.strategy.IStrategy;
import br.com.cascao.realmofcard.util.Util;
import br.com.cascao.realmofcard.util.correio.WebServiceCorreio;

import org.springframework.stereotype.Component;

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
