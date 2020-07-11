package br.edu.les.realmofcard.dao;

import java.util.ArrayList;
import java.util.List;

import br.edu.les.realmofcard.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.les.realmofcard.repository.PessoaRepository;
import br.edu.les.realmofcard.repository.UsuarioRepository;

@Service
public class PessoaDAO implements IDAO {

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private UsuarioDAO usuarioDAO;

	@Autowired
	private TelefoneDAO telefoneDAO;
	@Override
	public EntidadeDominio salvar(EntidadeDominio entidade) {
		Pessoa pessoa = (Pessoa) entidade;
		pessoa.setUsuario( (Usuario) usuarioDAO.salvar(pessoa.getUsuario()));
		for (Telefone telefone : pessoa.getTelefones()) {
			telefone.setPessoa(pessoa);
		}
		pessoa = pessoaRepository.save((Pessoa)entidade);
		return pessoa;
	}
	
	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {

		List<EntidadeDominio> pessoas = new ArrayList<>();
		Pessoa pessoa = (Pessoa) entidade;

		if(pessoa.getId() != null){
			pessoas.add(pessoaRepository.findById(pessoa.getId()).get());
			return pessoas;
		}

		if(pessoa.getUsuario() != null
				&& pessoa.getUsuario().getId() != null){
			pessoas.add(pessoaRepository.findPessoaByUsuario_Id(pessoa.getUsuario().getId()));
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
		Usuario usuario = usuarioRepository.findUsuarioById(pessoa.getUsuario().getId());
		pessoa.getUsuario().setPassword(usuario.getPassword());
		pessoa.getUsuario().setTipoUsuario(usuario.getTipoUsuario());
		pessoa.getUsuario().setStatus(usuario.getStatus());
		for (Telefone telefone : pessoa.getTelefones()) {
			telefone.setPessoa(pessoa);
		}
		pessoa = pessoaRepository.save(pessoa);
		pessoa.getUsuario().setPassword(null);
	}

	@Override
	public void excluir(EntidadeDominio entidade) {
		Pessoa pessoa = pessoaRepository.findById(((Pessoa) entidade).getId()).get();
		pessoa.getUsuario().setStatus(Status.builder().id(2).build());
		usuarioRepository.save(pessoa.getUsuario());
	}
}
