package br.com.cascao.realmofcard.negocio.strategy.usuario;

import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.domain.Usuario;
import br.com.cascao.realmofcard.negocio.strategy.IStrategy;
import br.com.cascao.realmofcard.repository.UsuarioRepository;
import br.com.cascao.realmofcard.util.Criptografia;
import br.com.cascao.realmofcard.util.validator.StringValidador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaSenhaUsuario implements IStrategy{

	@Autowired
	StringValidador stringValidador;

	@Autowired
	UsuarioRepository usuarioRepository;

	@Override
	public String processar(EntidadeDominio entidade) {

		StringBuilder msg = new StringBuilder();

		if(entidade instanceof Usuario){
			Usuario usuario = (Usuario) entidade;
			usuario.setPassword(Criptografia.criptografar(usuario.getPassword()));
			if(!usuarioRepository.existsByEmailAndPassword(usuario.getEmail(), usuario.getPassword())){
				msg.append("Login ou senha incorreto");
			}
			usuario.setPassword(null);
		}
		
		return msg.toString();
	}

}
