package br.com.cascao.realmofcard.persistence;

import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.domain.Usuario;
import br.com.cascao.realmofcard.repository.TipoUsuarioRepository;
import br.com.cascao.realmofcard.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioPersistence implements IPersistence {

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	TipoUsuarioRepository tipoUsuarioRepository;

	@Override
	public EntidadeDominio salvar(EntidadeDominio entidade) {
		Usuario usuario = (Usuario) entidade;
		usuario.setTipoUsuario(tipoUsuarioRepository.findTipoUsuarioById(usuario.getTipoUsuario().getId()));
		return usuarioRepository.save(usuario);
	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {

		List<EntidadeDominio> usuarios = new ArrayList<>();
		Usuario usuario = (Usuario) entidade;

		if(usuario.getId() != null){
			usuarioRepository.findById(usuario.getId()).map(p -> usuarios.add(p));
			return usuarios;
		}

		if(usuario.getEmail() != null) {
			usuario.setId(usuarioRepository.findByEmail(usuario.getEmail()).getId());
			usuarios.add(usuario);
			return usuarios;
		}

		usuarioRepository.findAll().forEach(p -> usuarios.add(p));

		return usuarios;
	}

	@Override
	public void alterar(EntidadeDominio entidade) {
		Usuario usuario = (Usuario) entidade;
		usuario.setPassword(usuarioRepository.findUsuarioById(usuario.getId()).getPassword());
		entidade = usuarioRepository.save(usuario);
		usuario.setPassword(null);
	}

	@Override
	public void excluir(EntidadeDominio entidade) {
		Usuario usuario = (Usuario) entidade;
		usuarioRepository.deleteById(usuario.getId());
	}
}
