package br.edu.les.realmofcard.strategy.cartao_credito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.les.realmofcard.domain.CartaoCredito;
import br.edu.les.realmofcard.domain.EntidadeDominio;
import br.edu.les.realmofcard.strategy.IStrategy;
import br.edu.les.realmofcard.repository.CartaoCreditoRepository;
import br.edu.les.realmofcard.util.Util;

@Component
public class ValidaNumeroJaExiste implements IStrategy {
	
	@Autowired
	private CartaoCreditoRepository cartaoCreditoRepository;

    @Override
    public String processar(EntidadeDominio entidade) {
    	
    	StringBuilder msg = new StringBuilder();
    	
    	if(entidade instanceof CartaoCredito){

            CartaoCredito cartaoCredito = (CartaoCredito) entidade;

            if(Util.isNotNull(cartaoCredito.getNumero())){
                if(cartaoCreditoRepository.existsByNumero(cartaoCredito.getNumero())){
                    msg.append("N�mero de cart�o de cr�dito j� existe.");
                }
            }
        }

        return msg.toString();
    }
}
