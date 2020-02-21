package br.com.cascao.realmofcard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.domain.Pessoa;

public class PessoaDAO extends AbstractDAO{

	public PessoaDAO(Connection connection, String table, String idTable) {
		super(connection, table, idTable);
		// TODO Auto-generated constructor stub
	}
	
	public PessoaDAO(String table, String idTable) {
		super( table, idTable);
		// TODO Auto-generated constructor stub
	}
	
	public PessoaDAO() {
		super( "pessoa", "id");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void salvar(EntidadeDominio entidade) {

		Pessoa pessoa = (Pessoa) entidade;

		openConnection();
		
		StringBuilder sql = new StringBuilder();
		sql.append("Insert into pessoa (pes_nome, pes_data_nascimento, pes_sexo) ");
		sql.append("VALUES (?,?,?)");
		
		try {
			
			PreparedStatement pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			pst.setString(1,pessoa.getNome());
			pst.setDate(2,new java.sql.Date(pessoa.getDataNascimento().getTime()));
			pst.setString(3, pessoa.getSexo());
			pst.executeUpdate();
			ResultSet rsPessoa = pst.getGeneratedKeys();
			if(rsPessoa.next()) {
				pessoa.setId(rsPessoa.getInt(1));
			}
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void alterar(EntidadeDominio entidade) {
		Pessoa pessoa = (Pessoa) entidade;

		openConnection();
		
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE pessoa SET pes_nome = ?, pes_data_nascimento = ?, pes_sexo = ? ");
		sql.append("WHERE id = ?");
		
		try {
			
			PreparedStatement pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			pst.setString(1,pessoa.getNome());
			pst.setDate(2,new java.sql.Date(pessoa.getDataNascimento().getTime()));
			pst.setString(3, pessoa.getSexo());
			pst.setInt(4, pessoa.getId());
			pst.execute();
			connection.commit();
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public EntidadeDominio visualizar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		
		List<EntidadeDominio> pessoas = new ArrayList<EntidadeDominio>();
		Pessoa pessoa = (Pessoa) entidade;
		
		openConnection();
		
		StringBuilder sql = new StringBuilder();
		PreparedStatement pst = null;
		
		if(pessoa.getId() == null || pessoa.getNome() == "") {
			sql.append("Select * from pessoa");
			try {
				
				pst = connection.prepareStatement(sql.toString(),Statement.RETURN_GENERATED_KEYS);
				ResultSet rs = pst.executeQuery();
				
				while(rs.next()) {
					Pessoa pessoaConsultada = new Pessoa();
					pessoaConsultada.setNome(rs.getString("pes_nome"));
					pessoaConsultada.setDataNascimento(rs.getDate("pes_data_nascimento"));
					pessoaConsultada.setSexo(rs.getString("pes_sexo"));
					pessoaConsultada.setId(rs.getInt("id"));
					pessoas.add(pessoaConsultada);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return pessoas;
		}
		
		if(pessoa.getId() != null) {
			
			sql.append("Select * from pessoa where id = ?");
			
			try {
				
				pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
				pst.setInt(1, pessoa.getId());
				ResultSet rs = pst.executeQuery();
				
				if(rs.next()) {
					pessoa.setNome(rs.getString("pes_nome"));
					pessoa.setDtCadastro(rs.getDate("pes_data_nascimento"));
					pessoa.setSexo(rs.getString("pes_sexo"));
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return pessoas;
	}
}
