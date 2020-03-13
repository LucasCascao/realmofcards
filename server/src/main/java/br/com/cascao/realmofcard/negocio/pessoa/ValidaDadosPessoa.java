package br.com.cascao.realmofcard.negocio.pessoa;

import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.domain.Pessoa;
import br.com.cascao.realmofcard.negocio.IStrategy;
import org.springframework.stereotype.Component;

@Component
public class ValidaDadosPessoa implements IStrategy{

	@Override
	public String processar(EntidadeDominio entidade) {

		Pessoa pessoa = (Pessoa) entidade;
		StringBuilder msg = new StringBuilder();
		
		if (pessoa.getNome() == null || pessoa.getNome().trim().equals("")) {
            msg.append("O campo nome é obrigatório.");
        }
		
		if (pessoa.getSexo() == null || pessoa.getSexo().trim().equals("")) {
            msg.append("O campo sexo é obrigatório.");
        }
		
		if (pessoa.getDataNascimento() == null || pessoa.getDataNascimento().toString().equals("")) {
            msg.append("O campo data de nascimento é obrigatório.");
        }

		if(pessoa.getCpf() == null || pessoa.getCpf().trim().equals("")){
			msg.append("O campo CPF é obrigatório.");
		}else if( pessoa.getCpf().length() != 11){
			msg.append("CPF invalido.");
		}

		if(pessoa.getSobrenome() == null || pessoa.getSobrenome().trim().equals("")){
			msg.append("O campo sobrenome é obrigatório.");
		}
		
		return msg.toString();
	}
}
