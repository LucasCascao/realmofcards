package br.edu.les.realmofcard.strategy.telefone;

import br.edu.les.realmofcard.domain.EntidadeDominio;
import br.edu.les.realmofcard.domain.Pessoa;
import br.edu.les.realmofcard.domain.Telefone;
import br.edu.les.realmofcard.strategy.IStrategy;
import br.edu.les.realmofcard.util.Util;
import br.edu.les.realmofcard.util.validador.ValidadorString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaDadosTelefone implements IStrategy {

	@Autowired
	ValidadorString validadorString;

	@Override
	public String processar(final EntidadeDominio entidade) {

		StringBuilder msg = new StringBuilder();

		if(entidade instanceof Pessoa){
			Pessoa pessoa = (Pessoa) entidade;
			Boolean naoTemTeledoneValido = false;
			if(Util.isNotNull(pessoa.getTelefones())){
				if(pessoa.getTelefones().size() > 0){
					for (Telefone telefone : pessoa.getTelefones()) {
						if(Util.isNull(telefone.getNumero())){
							naoTemTeledoneValido = true;
						} else if( telefone.getNumero().length() != 8 && telefone.getNumero().length() != 9){
							naoTemTeledoneValido = true;
						}
						if(Util.isNull(telefone.getDdd())){
							msg.append(validadorString.validar(null, "DDD"));
						} else if(telefone.getDdd().length() != 3) {
							msg.append("Numero de DDD invalido.");
						}
					}
				} else {
					naoTemTeledoneValido = true;
				}
			} else {
				naoTemTeledoneValido = true;
			}
			if(naoTemTeledoneValido){
				msg.append(validadorString.validar(null, "telefone"));
			}
		}
		return msg.toString();
	}
}
