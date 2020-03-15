package br.com.cascao.realmofcard.service;

import java.util.ArrayList;
import java.util.List;

import br.com.cascao.realmofcard.domain.Usuario;
import br.com.cascao.realmofcard.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.domain.Pessoa;
import br.com.cascao.realmofcard.repository.PessoaRepository;

@Repository
public class PessoaService implements IService{

	@Autowired
	PessoaRepository pessoaRepository;

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	UsuarioService usuarioService;

	@Override
	public EntidadeDominio salvar(EntidadeDominio entidade) {
		Pessoa pessoa = (Pessoa) entidade;
		pessoa.setUsuario( (Usuario) usuarioService.salvar(pessoa.getUsuario()));
		return pessoaRepository.save((Pessoa)entidade);
	}
	
	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		List<EntidadeDominio> pessoas = new ArrayList<>();
		Pessoa pessoa = (Pessoa) entidade;
		if(pessoa.getId() != null){
			pessoaRepository.findById(pessoa.getId()).map(p -> pessoas.add(p));
			pessoa = (Pessoa) pessoas.get(0);
			pessoa.getUsuario().setPassword(null);
			return pessoas;
		}

		pessoaRepository.findAll().stream().forEach(p -> {
			p.getUsuario().setPassword(null);
			pessoas.add(p);
		});

		return pessoas;
	}

	@Override
	public void alterar(EntidadeDominio entidade) {
		entidade = pessoaRepository.save((Pessoa)entidade);
	}

	@Override
	public void excluir(EntidadeDominio entidade) {
		Pessoa pessoa = (Pessoa) entidade;
		pessoaRepository.deleteById(pessoa.getId());
	}
}
