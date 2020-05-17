package br.edu.les.realmofcard.strategy.carta;

import br.edu.les.realmofcard.strategy.IStrategy;
import org.springframework.stereotype.Component;

import br.edu.les.realmofcard.domain.Carta;
import br.edu.les.realmofcard.domain.EntidadeDominio;

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
