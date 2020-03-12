package br.com.cascao.realmofcard.service;

import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.domain.Pessoa;
import br.com.cascao.realmofcard.domain.Usuario;
import br.com.cascao.realmofcard.repository.UsuarioRepository;
import br.com.cascao.realmofcard.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UsuarioService implements IService{

	@Autowired
	UsuarioRepository usuarioDAO;

	@Override
	public EntidadeDominio salvar(EntidadeDominio entidade) {
		return usuarioDAO.save((Usuario) entidade);
	}
	
	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		List<EntidadeDominio> usuarios = new ArrayList<>();
		Usuario usuario = (Usuario) entidade;
		if(usuario.getId() != null){
			usuarioDAO.findById(entidade.getId()).map( p -> usuarios.add(p));
			return usuarios;
		}

		usuarioDAO.findAll().stream().forEach( p -> usuarios.add(p));

		return usuarios;
	}

	@Override
	public void alterar(EntidadeDominio entidade) {
		entidade = usuarioDAO.save((Usuario) entidade);
	}

	@Override
	public void excluir(EntidadeDominio entidade) {
		usuarioDAO.deleteById(entidade.getId());
	}
}
