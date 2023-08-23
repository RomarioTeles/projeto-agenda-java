package io.github.romario.bd.agenda.ddl;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import io.github.romario.bd.Conexao;

public class GeradorDasTabelas {

    private final String sql_create_contatos = "CREATE TABLE IF NOT EXISTS contatos(id INT AUTO_INCREMENT PRIMARY KEY, nome VARCHAR(50), tel VARCHAR(40), grupo_id INT)";

    private final String sql_create_grupos = "CREATE TABLE IF NOT EXISTS grupos(id INT AUTO_INCREMENT PRIMARY KEY, nome VARCHAR(50))";

    private final String sql_add_fk_grupos = "ALTER TABLE contatos ADD CONSTRAINT FK_CONTATOS_GRUPOS FOREIGN KEY (grupo_id) REFERENCES grupos(id)";
    
    public void gerar(Conexao conexao) throws ClassNotFoundException, SQLException{
        try(Connection con = conexao.criarConexao()){
            criarTabelaContatos(con);
            criarTabelaGrupos(con);
            try{
                associarTabelasContatosGrupos(con);
            }catch(SQLException e){
                log("Associação falhou: " + e.getMessage());
            }
        }
    }

    private void associarTabelasContatosGrupos(Connection con) throws SQLException {
        executeSQL(con, sql_add_fk_grupos);
    }

    private void criarTabelaGrupos(Connection con) throws SQLException {
        executeSQL(con, sql_create_grupos);
    }

    private void criarTabelaContatos(Connection con) throws SQLException {
        executeSQL(con, sql_create_contatos);
    }

    private void executeSQL(Connection con, String sql) throws SQLException{
        try (Statement stmt = con.createStatement()) {
            log("executando SQL: "+sql);
            stmt.execute(sql);
            log("SQL executado com sucesso!");
        }
    }

    private void log(String mensagem){
        System.out.println(mensagem);
    }

}
