package io.github.romario.bd.agenda.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import io.github.romario.bd.Conexao;
import io.github.romario.bd.agenda.dml.AbstractDML;
import io.github.romario.domain.entity.Entity;

public abstract class AbstractDAO<T> {

    private Conexao conexao;

    private AbstractDML<T> dml;

    public AbstractDAO(Conexao conexao, AbstractDML<T> dml) {
        this.conexao = conexao;
        this.dml = dml;
    }

    public T insert(T entity) throws Exception {
        try (Connection con = conexao.criarConexao();
                Statement stmt = con.createStatement()) {

            long prevId = getUltimoId();
            stmt.executeUpdate(dml.getInsert(entity));
            long idEntity = getUltimoId();
            if (prevId != idEntity) {
                ((Entity) entity).setId(idEntity);
                return entity;
            } else {
                throw new Exception("inserção mal sucedida!");
            }
        }
    }

    public int update(long id, T entity) throws Exception {
        try (Connection con = conexao.criarConexao();
                Statement stmt = con.createStatement()) {

            String sql = dml.getUpdate(id, entity);
            System.out.println("Executando: " + sql);
            return stmt.executeUpdate(sql);
        }
    }

    public List<T> selectAllByExemple(T exemplo) throws SQLException, ClassNotFoundException {
        try (Connection con = conexao.criarConexao();
                Statement stmt = con.createStatement()) {
            String sql = dml.getSelectAllWhere(exemplo, 100, 0, "id");
            System.out.println("Executando: " + sql);
            ResultSet resultSet = stmt.executeQuery(sql);
            List<T> res = new ArrayList<>();
            while (resultSet.next()) {
                res.add(extraiObjetoApartirResultSet(resultSet));
            }
            return res;
        }
    }

    abstract T extraiObjetoApartirResultSet(ResultSet resultSet) throws SQLException;

    public T insertPreparedStatement(T entity) throws Exception {

        String sql = dml.getInsertComPrepatedStatement(entity);
        System.out.println("Executando: " + sql);
        try (Connection con = conexao.criarConexao();
                PreparedStatement stmt = con.prepareStatement(sql)) {
            dml.setParametrosDoPreparedStatement(stmt, entity);
            long prevId = getUltimoId();
            stmt.execute();
            long idEntity = getUltimoId();
            if (prevId != idEntity) {
                ((Entity) entity).setId(idEntity);
                return entity;
            } else {
                throw new Exception("inserção mal sucedida!");
            }
        }
    }

    public Long getUltimoId() throws ClassNotFoundException, SQLException {
        try (Connection con = conexao.criarConexao();
                Statement stmt = con.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(dml.getSelectLastId());
            long id = 0;
            if (resultSet.next())
                id = resultSet.getLong(1);
            return id;
        }
    }

    public void delete(long id) throws ClassNotFoundException, SQLException {
        try (Connection con = conexao.criarConexao();
                Statement stmt = con.createStatement()) {
            stmt.executeUpdate(dml.getDelete(id));
        }
    }

}
