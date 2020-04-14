package br.com.cascao.realmofcard.negocio.strategy.cartao_credito;

import br.com.cascao.realmofcard.domain.CartaoCredito;
import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.negocio.strategy.IStrategy;
import br.com.cascao.realmofcard.repository.CartaoCreditoRepository;
import br.com.cascao.realmofcard.util.Util;
import br.com.cascao.realmofcard.util.validador.ValidadorString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ValidaNumeroJaExiste implements IStrategy {

    @Autowired
    CartaoCreditoRepository cartaoCreditoRepository;

    @Override
    public String processar(EntidadeDominio entidade) {

        StringBuilder msg = new StringBuilder();

        if(entidade instanceof CartaoCredito){

            CartaoCredito cartaoCredito = (CartaoCredito) entidade;

            if(Util.isNotNull(cartaoCredito.getNumero())){
                if(cartaoCreditoRepository.existsByNumero(cartaoCredito.getNumero())){
                    msg.append("Número de cartão de crédito já existe.");
                }
            }
        }

        return msg.toString();
    }
}
