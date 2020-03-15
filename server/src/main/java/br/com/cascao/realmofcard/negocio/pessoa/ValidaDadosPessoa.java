package br.com.cascao.realmofcard.negocio.pessoa;

import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.domain.Pessoa;
import br.com.cascao.realmofcard.negocio.IStrategy;
import br.com.cascao.realmofcard.negocio.usuario.ValidaDadosUsuario;
import br.com.cascao.realmofcard.validator.StringValidador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaDadosPessoa implements IStrategy{

	@Autowired
	StringValidador stringValidador;

	@Autowired
	ValidaDadosUsuario validaDadosUsuario;

	@Override
	public String processar(EntidadeDominio entidade) {

		Pessoa pessoa = (Pessoa) entidade;
		StringBuilder msg = new StringBuilder();

		msg.append(stringValidador.validar(pessoa.getNome(), "nome"));
		msg.append(stringValidador.validar(pessoa.getSobrenome(), "sobrenome"));
		if(pessoa.getId() == null){
			if(pessoa.getCpf() == null || pessoa.getCpf().trim().equals("")){
				msg.append("O campo CPF é obrigatório.");
			}else if( pessoa.getCpf().length() != 11){
				msg.append("CPF invalido.");
			}
		}
		msg.append(stringValidador.validar(pessoa.getDataNascimento(), "data de nascimento"));
		msg.append(stringValidador.validar(pessoa.getSexo(), "sexo"));
		
		return msg.toString();
	}
}
