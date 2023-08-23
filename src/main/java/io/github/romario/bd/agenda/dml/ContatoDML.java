package io.github.romario.bd.agenda.dml;


import java.util.LinkedHashMap;
import java.util.Map;

import io.github.romario.domain.entity.Contato;

public class ContatoDML extends AbstractDML<Contato>{

    public Map<String, Object> getRegistroBD(Contato contato) {
        Map<String, Object> registro = new LinkedHashMap<>();
        if (contato.getId() != null) {
            registro.put("id", contato.getId());
        }
        if (contato.getNome() != null) {
            registro.put("nome", contato.getNome());
        }
        if (contato.getTelefone() != null) {
            registro.put("tel", contato.getTelefone());
        }
        if (contato.getGrupoId() != null) {
            registro.put("grupo_id", contato.getGrupoId());
        }
        return registro;
    }

    @Override
    protected String getTableName() {
        return "contatos";
    }


}
