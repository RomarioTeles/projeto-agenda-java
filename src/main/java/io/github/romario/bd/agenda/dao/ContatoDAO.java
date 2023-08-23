package io.github.romario.bd.agenda.dao;
import java.sql.ResultSet;
import java.sql.SQLException;


import io.github.romario.bd.Conexao;
import io.github.romario.bd.agenda.dml.AbstractDML;
import io.github.romario.domain.entity.Contato;

public class ContatoDAO extends AbstractDAO<Contato>{

    public ContatoDAO(Conexao conexao, AbstractDML<Contato> dml) {
        super(conexao, dml);
    }

    @Override
    Contato extraiObjetoApartirResultSet(ResultSet resultSet) throws SQLException {
        return new Contato()
                .id(resultSet.getLong("id"))
                .nome(resultSet.getString("nome"))
                .telefone(resultSet.getString("tel"))
                .grupoId(resultSet.getLong("grupo_id"));
    }
    
}
