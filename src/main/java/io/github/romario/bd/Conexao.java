package io.github.romario.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    
    private String driver;

    private String host;

    private String porta;

    private String banco;

    private String usuario;

    private String senha;

    public Conexao(String driver, String host, String porta, String banco, String usuario, String senha) {
        this.driver = driver;
        this.host = host;
        this.porta = porta;
        this.banco = banco;
        this.usuario = usuario;
        this.senha = senha;
    }

    public Connection criarConexao() throws SQLException, ClassNotFoundException {
        String url = String.format("jdbc:mysql://%s:%s/%s", host, porta, banco);
        Class.forName(driver);
        return DriverManager.getConnection(url, usuario, senha);
    }
    

}
