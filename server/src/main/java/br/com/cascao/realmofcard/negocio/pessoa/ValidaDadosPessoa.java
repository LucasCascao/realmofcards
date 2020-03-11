package br.com.cascao.realmofcard.negocio.pessoa;

import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.domain.Pessoa;
import br.com.cascao.realmofcard.negocio.IStrategy;

public class ValidaDadosPessoa implements IStrategy{

	@Override
	public String processar(EntidadeDominio entidade) {

		Pessoa pessoa = (Pessoa) entidade;
		StringBuilder msg = new StringBuilder();
		
		if (pessoa.getNome() == null) {
            msg.append("O campo nome é obrigatório.");
        }
		
		if (pessoa.getSexo() == null) {
            msg.append("O campo sexo é obrigatório.");
        }
		
		if (pessoa.getDataNascimento() == null) {
            msg.append("O campo data de nascimento é obrigatório.");
        }

		if(pessoa.getCpf() == null){
			msg.append("O campo CPF é obrigatório.");
		}

		if(pessoa.getEmail() == null || pessoa.getEmail().trim().equals("")){
			msg.append("O campo email é obrigatório.");
		}

		if(pessoa.getUsername() == null || pessoa.getUsername().trim().equals("")){
			msg.append("O campo usuário é obrigatório.");
		}

		if(pessoa.getPassword() == null || pessoa.getPassword().trim().equals("")){
			msg.append("O campo senha é obrigatório.");
		}

		if(pessoa.getSobrenome() == null || pessoa.getSobrenome().trim().equals("")){
			msg.append("O campo sobrenome é obrigatório.");
		}
		
		return msg.toString();
	}

}
