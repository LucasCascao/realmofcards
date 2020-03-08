package br.com.cascao.realmofcard.dao;

import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.domain.Pessoa;
import br.com.cascao.realmofcard.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.List;

@Service
public class PessoaJPADAO extends AbstractDAO{

	@Autowired
	private PessoaRepository pessoaRepository;

	public PessoaJPADAO(Connection connection, String table, String idTable) {
		super(connection, table, idTable);
		// TODO Auto-generated constructor stub
	}

	public PessoaJPADAO(String table, String idTable) {
		super( table, idTable);
		// TODO Auto-generated constructor stub
	}

	public PessoaJPADAO() {
		super( "pessoa", "id");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void salvar(EntidadeDominio entidade) {
		 entidade = pessoaRepository.save((Pessoa)entidade);
	}

	@Override
	public void alterar(EntidadeDominio entidade) {

	}

	@Override
	public EntidadeDominio visualizar(EntidadeDominio entidade) {
		return null;
	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {

		List<Pessoa> pessoas = null;
//		Pessoa pessoa = pessoaRepository.findById(entidade.getId());

//		if(entidade.getId() != null){
//			pessoas = new ArrayList<>();
//			pessoas.add();
//			return pessoas;
//		}else{

			return (List<EntidadeDominio>) (List)  pessoaRepository.findAll();

//		}
	}
}
