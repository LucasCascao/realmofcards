
package br.com.cascao.realmofcard.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConexaoPostgresSQL {
    private static String driver;
    private static String url;
    private static String user;
    private static String password;

    public static Connection getConecction() throws ClassNotFoundException, SQLException{

            driver = "org.postgresql.Driver";
            url = "jdbc:postgresql://localhost:5432/templateexemplo";
            user = "postgres";
            password = "48299871";

            Class.forName(driver);		// Solicita que seja acriada uma instancuia da classe com base no nome da String

            Connection conexao = DriverManager.getConnection(url, user, password);

            return conexao;
    }
}
