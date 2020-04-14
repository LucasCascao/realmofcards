package br.com.cascao.realmofcard.negocio.strategy.cartao_credito;

import br.com.cascao.realmofcard.domain.CartaoCredito;
import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.negocio.strategy.IStrategy;
import br.com.cascao.realmofcard.util.Util;
import br.com.cascao.realmofcard.util.validador.ValidadorString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ValidaValidadeCartao implements IStrategy {

    @Autowired
    ValidadorString validadorString;

    @Override
    public String processar(EntidadeDominio entidade) {

        StringBuilder msg = new StringBuilder();

        if(entidade instanceof CartaoCredito){

            CartaoCredito cartaoCredito = (CartaoCredito) entidade;
            if(Util.isNotNull(cartaoCredito.getVencimentoAno())
                && Util.isNotNull(cartaoCredito.getVencimentoMes())){

                Integer mesVencimento = Integer.parseInt(cartaoCredito.getVencimentoMes());
                Integer anoVencimento = Integer.parseInt(cartaoCredito.getVencimentoAno());

                Integer anoAtual = LocalDate.now().getYear();
                Integer mesAtual = LocalDate.now().getMonth().getValue();

                if(anoVencimento < anoAtual || (anoVencimento.equals(anoAtual) && mesVencimento < mesAtual) ){
                    msg.append("Cartão está vencido.");
                }
            }
        }

        return msg.toString();
    }
}
