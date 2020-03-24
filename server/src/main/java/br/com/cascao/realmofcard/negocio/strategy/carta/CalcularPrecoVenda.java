package br.com.cascao.realmofcard.negocio.strategy.carta;

import br.com.cascao.realmofcard.domain.Carta;
import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.negocio.strategy.IStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CalcularPrecoVenda implements IStrategy {

    @Override
    public String processar(EntidadeDominio entidade) {

        StringBuilder msg = new StringBuilder();

        if(entidade instanceof Carta){
            Carta carta = (Carta) entidade;
            if(carta.getValorCompra() != null && carta.getPrecificacao() != null){
                carta.setValorVenda(
                        carta.getValorCompra() + (carta.getValorCompra() * (carta.getPrecificacao() / 100))
                );
            }
        }

        return null;
    }
}
