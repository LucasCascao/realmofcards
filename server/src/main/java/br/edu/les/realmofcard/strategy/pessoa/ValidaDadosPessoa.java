package br.edu.les.realmofcard.strategy.pessoa;

import br.edu.les.realmofcard.strategy.IStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.les.realmofcard.domain.EntidadeDominio;
import br.edu.les.realmofcard.domain.Pessoa;
import br.edu.les.realmofcard.util.validador.ValidadorString;

@Component
public class ValidaDadosPessoa implements IStrategy {

	@Autowired
	ValidadorString validadorString;

	@Override
	public String processar(final EntidadeDominio entidade) {

		Pessoa pessoa = (Pessoa) entidade;
		StringBuilder msg = new StringBuilder();

		msg.append(validadorString.validar(pessoa.getNome(), "nome"));
		msg.append(validadorString.validar(pessoa.getSobrenome(), "sobrenome"));
		if(pessoa.getId() == null){
			if(pessoa.getCpf() == null || pessoa.getCpf().trim().equals("")){
				msg.append("O campo CPF é obrigatório.");
			}else if( pessoa.getCpf().length() != 11){
				msg.append("CPF invalido.");
			}
		}
		msg.append(validadorString.validar(pessoa.getDataNascimento(), "data de nascimento"));
		msg.append(validadorString.validar(pessoa.getSexo(), "sexo"));
		
		return msg.toString();
	}
}
