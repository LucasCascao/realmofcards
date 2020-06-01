package br.edu.les.realmofcard.strategy.transicao;

import br.edu.les.realmofcard.domain.EntidadeDominio;
import br.edu.les.realmofcard.domain.Transicao;
import br.edu.les.realmofcard.strategy.IStrategy;
import br.edu.les.realmofcard.util.GeradorCodigo;
import org.springframework.stereotype.Component;

@Component
public class GeraCodigoTransacaoTransicao implements IStrategy {

	@Override
	public String processar(final EntidadeDominio entidade) {
		if(entidade instanceof Transicao){
			Transicao transicao = (Transicao) entidade;
			transicao.setCodigo(GeradorCodigo.gerarCodigoTransacao());
		}
		return null;
	}
}
