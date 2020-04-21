package br.com.pontodigital.controller;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Neto
 */
public class Banco {
    private Connection conexao;
    private String driver = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/pdesusti";
    private String usuario = "root";
    private String senha = "root";
    
    private void conectar() {
        try {
            Class.forName(driver);
            conexao = (Connection) DriverManager.getConnection(url, usuario, senha);
        } catch(ClassNotFoundException e) {
            System.out.println();
        } catch(SQLException e) {
            System.out.println();
        }
    }
    
    private Connection getConexao() {
        if (conexao == null) {
            conectar();
        }
        return conexao;
    }    
    
    public void executarComando(String comando, ArrayList<String> valores) {
        try {
            PreparedStatement stmt = getConexao().prepareStatement(comando);
            int indice = 1;
            
            for (String valor: valores) {
                stmt.setString(indice, valor);
                indice++;
            }
        } catch(SQLException e) {
            System.out.println("Erro ao Executar o Comando!");
            //e.printStackTrace();
        }
    }
    
    public ResultSet executarConsulta(String consulta) {
        try {
            PreparedStatement stmt = getConexao().prepareStatement(consulta);
            return stmt.executeQuery();
        } catch (SQLException e) {
            System.out.println("Erro ao Executar a Consulta!");
            //e.printStackTrace();
        }
        return null;
    }
}
