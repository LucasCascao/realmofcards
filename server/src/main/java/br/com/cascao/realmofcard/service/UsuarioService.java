package br.com.cascao.realmofcard.service;

import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.domain.Usuario;
import br.com.cascao.realmofcard.repository.PessoaRepository;
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
	PessoaRepository pessoaRepository;

	@Override
	public EntidadeDominio salvar(EntidadeDominio entidade) {
		Usuario usuario = (Usuario) entidade;
		usuario.setPessoa(pessoaRepository.save(usuario.getPessoa()));
		return usuarioRepository.save(usuario);
	}
	
	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {

		List<EntidadeDominio> usuarios = new ArrayList<>();
		Usuario usuario = (Usuario) entidade;

		if(usuario.getId() != null){
			usuarioRepository.findById(entidade.getId()).map(p -> usuarios.add(p));
			return usuarios;
		}

		if(usuarioRepository.existsByEmailAndPassword(usuario.getEmail(), usuario.getPassword())) {
			usuario.setPassword(null);
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
		usuarioRepository.deleteById(entidade.getId());
	}
}
