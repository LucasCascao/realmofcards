package br.com.cascao.realmofcard.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.domain.Pessoa;
import br.com.cascao.realmofcard.repository.PessoaDAO;
import br.com.cascao.realmofcard.service.IService;

@Repository
public class PessoaService implements IService{

	@Autowired
	PessoaDAO pessoaDAO;

	@Override
	public EntidadeDominio salvar(EntidadeDominio entidade) {
		return pessoaDAO.save((Pessoa)entidade);
	}
	
	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		List<EntidadeDominio> pessoas = new ArrayList<EntidadeDominio>();
		if(entidade.getId() != null){
			pessoaDAO.findById(entidade.getId()).map( p -> pessoas.add(p));
			return pessoas;
		}
		pessoaDAO.findAll().stream().forEach(p -> pessoas.add(p));
		return pessoas;
	}

	@Override
	public void alterar(EntidadeDominio entidade) {
		entidade = pessoaDAO.save((Pessoa)entidade);
	}

	@Override
	public void excluir(EntidadeDominio entidade) {
		pessoaDAO.deleteById(entidade.getId());
	}

	@Override
	public EntidadeDominio visualizar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
