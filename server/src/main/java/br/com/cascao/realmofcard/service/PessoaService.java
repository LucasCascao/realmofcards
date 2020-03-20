package br.com.cascao.realmofcard.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
			pessoa = pessoaRepository.findPessoaByUsuario_Id(pessoa.getId());
			pessoa.getUsuario().setPassword(null);
			pessoas.add(pessoa);
			return pessoas;
		}

		pessoaRepository.findAll().forEach(p -> {
			p.getUsuario().setPassword(null);
			pessoas.add(p);
		});

		return pessoas;
	}

	@Override
	public void alterar(EntidadeDominio entidade) {
		Pessoa  pessoa = (Pessoa) entidade;
		pessoa.getUsuario().setPassword(usuarioRepository.findUsuarioById(pessoa.getUsuario().getId()).getPassword());
		pessoa = pessoaRepository.save(pessoa);
		pessoa.getUsuario().setPassword(null);
	}

	@Override
	public void excluir(EntidadeDominio entidade) {
		Pessoa pessoa = (Pessoa) entidade;
		pessoaRepository.deleteById(pessoa.getId());
	}
}
