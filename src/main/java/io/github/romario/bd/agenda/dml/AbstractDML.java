package io.github.romario.bd.agenda.dml;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public abstract class AbstractDML<T> {

    protected abstract String getTableName();

    public String getSelectLastId() {
        return "SELECT MAX(id) from "+ getTableName();
    }

    public String getSelectAll(int limit, int offset, String orderByColumn) {
        return String.format("SELECT * from entitys ORDER BY %s LIMIT %s OFFSET %s", orderByColumn, limit, offset);
    }

    public String getSelectAllWhere(T exemplo, int limit, int offset, String orderByColumn) {
        List<String> sb = new ArrayList<>();
        if (exemplo != null) {
            Map<String, Object> columnsWhere = getRegistroBD(exemplo);
            columnsWhere.forEach((column, value) -> {
                sb.add(String.format(" %s = '%s' AND ", column, value));
            });
        }
        sb.add(" 1=1 ");
        return String.format("SELECT * from %s WHERE %s ORDER BY %s LIMIT %s OFFSET %s", getTableName(), String.join("", sb) ,orderByColumn, limit, offset);
    }

    public String getInsert(T entity) {
        String insertStatementTemp = "INSERT INTO %s (%s) VALUES (%s)";
        Map<String, Object> registroSemId = new LinkedHashMap<>();
        registroSemId.putAll(getRegistroBD(entity));
        registroSemId.remove("id");
        String columns = String.join(",", registroSemId.keySet());
        String values = String.join("','",
                registroSemId.values().stream().map(String::valueOf).collect(Collectors.toList()));
        return String.format(insertStatementTemp, getTableName(), columns, "'" + values + "'");
    }

    public String getInsertComPrepatedStatement(T entity) {
        String insertStatementTemp = "INSERT INTO %s (%s) VALUES (%s)";
        Map<String, Object> registroSemId = getRegistroBD(entity);
        registroSemId.remove("id");
        String columns = String.join(",", registroSemId.keySet());
        String values = String.join(",", registroSemId.values().stream().map(i -> "?").collect(Collectors.toList()));
        return String.format(insertStatementTemp, getTableName(), columns, values);
    }

    public String getUpdate(Long id, T entity) {
        String updateStatement = "UPDATE %s SET %s WHERE id=%s";
        Map<String, Object> registroSemId = getRegistroBD(entity);
        registroSemId.remove("id");
        List<String> sb = new ArrayList<>();
        registroSemId.forEach((column, value) -> {
            sb.add(String.format(" %s = '%s' ", column, value));
        });
        return String.format(updateStatement,getTableName(), String.join(",", sb), id);
    }

    public String getDelete(Long id) {
        return "DELETE FROM "+ getTableName() +" WHERE id = '" + id + "'";
    }

    public void setParametrosDoPreparedStatement(PreparedStatement stmt, T entity) {
        Map<String, Object> registro = getRegistroBD(entity);
        registro.remove("id");
        List<Object> values = new ArrayList<>(registro.values());
        IntStream.range(1, values.size() + 1).forEach(index -> {
            Object valor = values.get(index - 1);
            try {
                if (valor instanceof Integer) {
                    stmt.setInt(index, (Integer) valor);
                } else if (valor instanceof Long) {
                    stmt.setLong(index, (long) valor);
                } else if (valor instanceof String) {
                    stmt.setString(index, String.valueOf(valor));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    protected abstract Map<String, Object> getRegistroBD(T entity);



}
