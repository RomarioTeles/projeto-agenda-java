package io.github.romario.bd.agenda.dao;
import java.sql.ResultSet;
import java.sql.SQLException;


import io.github.romario.bd.Conexao;
import io.github.romario.bd.agenda.dml.AbstractDML;
import io.github.romario.domain.entity.Grupo;

public class GrupoDAO extends AbstractDAO<Grupo>{

    public GrupoDAO(Conexao conexao, AbstractDML<Grupo> dml) {
        super(conexao, dml);
    }

    @Override
    public Grupo extraiObjetoApartirResultSet(ResultSet resultSet) throws SQLException {
        return new Grupo()
                .id(resultSet.getLong("id"))
                .nome(resultSet.getString("nome"));
    }
    
}
