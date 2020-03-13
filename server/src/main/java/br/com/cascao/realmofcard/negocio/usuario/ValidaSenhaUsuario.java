package br.com.cascao.realmofcard.negocio.usuario;

import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.domain.Pessoa;
import br.com.cascao.realmofcard.domain.Usuario;
import br.com.cascao.realmofcard.negocio.IStrategy;
import org.springframework.stereotype.Component;

@Component
public class ValidaSenhaUsuario implements IStrategy{

	@Override
	public String processar(EntidadeDominio entidade) {

		Usuario usuario = (Usuario) entidade;
		StringBuilder msg = new StringBuilder();

		if(usuario.getEmail() == null){
			msg.append("O campo email é obrigatório.");
		}

		if(usuario.getPassword() == null){
			msg.append("O campo senha é obrigatório.");
		}
		
		return msg.toString();
	}

}
