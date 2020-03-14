package br.com.cascao.realmofcard.negocio.usuario;

import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.domain.Pessoa;
import br.com.cascao.realmofcard.domain.Usuario;
import br.com.cascao.realmofcard.negocio.IStrategy;
import br.com.cascao.realmofcard.validator.StringValidador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaSenhaUsuario implements IStrategy{

	@Autowired
	StringValidador stringValidador;

	@Override
	public String processar(EntidadeDominio entidade) {

		Usuario usuario = (Usuario) entidade;
		StringBuilder msg = new StringBuilder();

		stringValidador.validar(usuario.getEmail(), "email");
		stringValidador.validar(usuario.getPassword(), "senha");
		
		return msg.toString();
	}

}
