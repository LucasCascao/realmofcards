package br.com.cascao.realmofcard.service;

import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.domain.TipoUsuario;
import br.com.cascao.realmofcard.domain.Usuario;
import br.com.cascao.realmofcard.repository.PessoaRepository;
import br.com.cascao.realmofcard.repository.TipoUsuarioRepository;
import br.com.cascao.realmofcard.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UsuarioService implements IService{

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	TipoUsuarioRepository tipoUsuarioRepository;

	@Override
	public EntidadeDominio salvar(EntidadeDominio entidade) {
		Usuario usuario = (Usuario) entidade;
		usuario.setTipoUsuario(tipoUsuarioRepository.findAll().get(usuario.getTipoUsuario().getId()-1));
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
			usuarios.add(usuario);
			return usuarios;
		}

		usuarioRepository.findAll().stream().forEach(p -> usuarios.add(p));

		return usuarios;
	}

	@Override
	public void alterar(EntidadeDominio entidade) {
		entidade = usuarioRepository.save((Usuario) entidade);
	}

	@Override
	public void excluir(EntidadeDominio entidade) {
		Usuario usuario = (Usuario) entidade;
		usuarioRepository.deleteById(usuario.getId());
	}
}
