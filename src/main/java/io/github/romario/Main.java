package io.github.romario;


import java.util.List;

import io.github.romario.bd.Conexao;
import io.github.romario.bd.agenda.dao.ContatoDAO;
import io.github.romario.bd.agenda.dao.GrupoDAO;
import io.github.romario.bd.agenda.ddl.GeradorDasTabelas;
import io.github.romario.bd.agenda.dml.ContatoDML;
import io.github.romario.bd.agenda.dml.GrupoDML;
import io.github.romario.domain.entity.Contato;
import io.github.romario.domain.entity.Grupo;

public class Main {
    public static void main(String[] args) throws Exception {
        
        Conexao conexao = new Conexao("com.mysql.jdbc.Driver", "localhost", "3306", "agenda", "root", "cod3r");
        GeradorDasTabelas geradorDasTabelas = new GeradorDasTabelas();
        geradorDasTabelas.gerar(conexao);
        ContatoDAO contatoDAO = new ContatoDAO(conexao, new ContatoDML());
        GrupoDAO grupoDAO = new GrupoDAO(conexao, new GrupoDML());
        

        Grupo casa = grupoDAO.insert(new Grupo().nome("CASA"));
        Grupo academia = grupoDAO.insert(new Grupo().nome("ACADEMIA"));
        Grupo dataprev = grupoDAO.insert(new Grupo().nome("TRABALHO"));
        Grupo igreja = grupoDAO.insert(new Grupo().nome("IGREJA"));

        List<Grupo> grupos = grupoDAO.selectAllByExemple(null);

        grupos.forEach(System.out::println);

        grupoDAO.delete(igreja.getId());

        grupoDAO.update(dataprev.getId(), dataprev.nome("DATAPREV"));

        grupos = grupoDAO.selectAllByExemple(null);

        grupos.forEach(System.out::println);

        
        contatoDAO.insert(new Contato().nome("Regis").telefone("88 9 9999-9999").grupoId(casa.getId()));
        contatoDAO.insert(new Contato().nome("Maria").telefone("88 9 9999-9999").grupoId(casa.getId()));
        contatoDAO.insert(new Contato().nome("Jose").telefone("88 9 9999-9999").grupoId(casa.getId()));
        contatoDAO.insert(new Contato().nome("Bia").telefone("88 9 9999-9999").grupoId(casa.getId()));

        contatoDAO.insert(new Contato().nome("Sam").telefone("88 9 9999-9999").grupoId(dataprev.getId()));
        contatoDAO.insert(new Contato().nome("Nat").telefone("88 9 9999-9999").grupoId(dataprev.getId()));
        contatoDAO.insert(new Contato().nome("john").telefone("88 9 9999-9999").grupoId(dataprev.getId()));
        contatoDAO.insert(new Contato().nome("Rock").telefone("88 9 9999-9999").grupoId(dataprev.getId()));

        Contato erin = contatoDAO.insert(new Contato().nome("Eri").telefone("88 9 9999-9999").grupoId(academia.getId()));
        Contato caio = contatoDAO.insert(new Contato().nome("Caio").telefone("88 9 9999-9999").grupoId(academia.getId()));
        
        
        List<Contato> lista = contatoDAO.selectAllByExemple(null);

        lista.forEach(System.out::println);

        contatoDAO.update(erin.getId(), erin.nome("Eri Academia"));
        contatoDAO.delete(caio.getId());

        lista = contatoDAO.selectAllByExemple(null);

        lista.forEach(System.out::println);
    }
}