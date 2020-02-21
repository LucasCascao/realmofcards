package br.com.cascao.realmofcard.negocio.pessoa;

import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.domain.Pessoa;
import br.com.cascao.realmofcard.negocio.IStrategy;

public class ValidaDadosPessoa implements IStrategy{

	@Override
	public String processar(EntidadeDominio entidade) {
		Pessoa pessoa = (Pessoa) entidade;
		StringBuilder msg = new StringBuilder();
		
		System.out.println(pessoa.getNome());
		
		if (pessoa.getNome().trim().equals("")) {
            msg.append("O nome � obrigat�rio.");
        }
		
		if (pessoa.getSexo().trim().equals("")) {
            msg.append("O sexo � obrigat�rio.");
        }
		
		if (pessoa.getDataNascimento().toString().trim().equals("")) {
            msg.append("O data de nascimento � obrigat�rio.");
        }
		
		return msg.toString();
	}

}
