package br.com.cascao.realmofcard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.cascao.realmofcard.domain.EntidadeDominio;
import br.com.cascao.realmofcard.util.ConexaoPostgresSQL;

public abstract class AbstractDAO implements IDAO {
	
    protected Connection connection;
    protected String table;     // Nome da tabela que irá ser manipulada
    protected String idtable;   // Nome da coluna que contem o id a ser manipulado
    protected boolean controlTransaction = true;    // Controla as transações, se ja estiver aberta ou se ela está fechada

    
    // Contrutor 1: Recebe uma conexão já aberta e os dados da tabela e coluna de banco de dados.
    public AbstractDAO(Connection connection, String table, String idTable){	
        this.connection = connection;
        this.table = table;
        this.idtable = idTable;
    }

    // Contrutor 2: Recebe aoenas os dados da tabela e da coluna do banco de dados.
    public AbstractDAO(String table, String idTable){
        this.table = table;
        this.idtable = idTable;
    }

    // Verifica se a conexão ja está aberto caso não esteja abre, para a execução dos comandos.
    protected void openConnection(){
        try{
                
                if (connection == null || connection.isClosed()){
                        connection = ConexaoPostgresSQL.getConecction();
                        connection.setAutoCommit(false);
                }
        }catch(ClassNotFoundException | SQLException e){
                e.printStackTrace();
        }
    }

    // Exclui os dados de uma determinada tabela com base no id fornecido.
    @Override
    public void excluir(EntidadeDominio entidadeDominio) {
        // TODO Auto-generated method stub
        openConnection();
        PreparedStatement pst = null;
        StringBuilder sb = new StringBuilder();
        // DELETE FROM NomeTABELA WHERE NomePK = ?
        sb.append("DELETE FROM ");
        sb.append(table);
        sb.append(" WHERE ");
        sb.append(idtable);
        sb.append(" = ");
        sb.append("?");
        try{
            pst = connection.prepareStatement(sb.toString());	// manda para o pst(preparedStatement) o sql que sera executado no banco
            pst.setInt(1, entidadeDominio.getId());		// Substitui o primeiro ?(parametro) pelo valor fornecido
            pst.executeUpdate();						// executa a update no banco
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
