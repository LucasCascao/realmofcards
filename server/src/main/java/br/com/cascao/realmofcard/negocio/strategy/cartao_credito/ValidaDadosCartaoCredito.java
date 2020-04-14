package br.com.cascao.realmofcard.negocio.strategy.cartao_credito;

import br.com.cascao.realmofcard.domain.CartaoCredito;
import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.negocio.strategy.IStrategy;
import br.com.cascao.realmofcard.util.validador.ValidadorString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaDadosCartaoCredito implements IStrategy {

    @Autowired
    ValidadorString validadorString;

    @Override
    public String processar(EntidadeDominio entidade) {

        StringBuilder msg = new StringBuilder();

        if(entidade instanceof CartaoCredito){

            CartaoCredito cartaoCredito = (CartaoCredito) entidade;
            msg.append(validadorString.validar(cartaoCredito.getNumero(), "numero do cartão"));
            msg.append(validadorString.validar(cartaoCredito.getCodigoSeguranca(), "código de segurança"));
            msg.append(validadorString.validar(cartaoCredito.getVencimentoMes(), "mês de vencimento"));
            msg.append(validadorString.validar(cartaoCredito.getVencimentoAno(), "ano de vencimento"));
            msg.append(validadorString.validar(cartaoCredito.getTitularNome(), "nome do titular do cartão"));
            msg.append(validadorString.validar(cartaoCredito.getBandeira(), "bandeira do cartão"));

        }

        return msg.toString();
    }
}
