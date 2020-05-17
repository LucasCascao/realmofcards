package br.edu.les.realmofcard.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.les.realmofcard.domain.EntidadeDominio;
import br.edu.les.realmofcard.domain.Pessoa;
import br.edu.les.realmofcard.domain.Usuario;
import br.edu.les.realmofcard.repository.PessoaRepository;
import br.edu.les.realmofcard.repository.UsuarioRepository;

@Service
public class PessoaDAO implements IDAO {

	@Autowired
	PessoaRepository pessoaRepository;

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	UsuarioDAO usuarioDAO;

	@Override
	public EntidadeDominio salvar(EntidadeDominio entidade) {
		Pessoa pessoa = (Pessoa) entidade;
		pessoa.setUsuario( (Usuario) usuarioDAO.salvar(pessoa.getUsuario()));
		return pessoaRepository.save((Pessoa)entidade);
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
		pessoa = pessoaRepository.save(pessoa);
		pessoa.getUsuario().setPassword(null);
	}

	@Override
	public void excluir(EntidadeDominio entidade) {
		Pessoa pessoa = (Pessoa) entidade;
		pessoaRepository.deleteById(pessoa.getId());
	}
}
