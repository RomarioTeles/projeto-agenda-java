package io.github.romario.bd.agenda.dml;


import java.util.LinkedHashMap;
import java.util.Map;

import io.github.romario.domain.entity.Grupo;

public class GrupoDML extends AbstractDML<Grupo>{

    public Map<String, Object> getRegistroBD(Grupo contato) {
        Map<String, Object> registro = new LinkedHashMap<>();
        if (contato.getId() != null) {
            registro.put("id", contato.getId());
        }
        if (contato.getNome() != null) {
            registro.put("nome", contato.getNome());
        }
        return registro;
    }

    @Override
    protected String getTableName() {
        return "grupos";
    }


}
