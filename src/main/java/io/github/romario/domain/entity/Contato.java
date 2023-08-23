package io.github.romario.domain.entity;

public class Contato extends Entity{
    
    private Long id;

    private String nome;

    private String telefone;

    private Long grupoId;

    public Contato id(long id){
        setId(id);
        return this;
    }

    public Contato nome(String nome){
        setNome(nome);
        return this;
    }

    public Contato telefone(String tel){
        setTelefone(tel);
        return this;
    }

    public Contato grupoId(Long grupoId){
        setGrupoId(grupoId);
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Long getGrupoId() {
        return grupoId;
    }

    public void setGrupoId(Long grupoId) {
        this.grupoId = grupoId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((telefone == null) ? 0 : telefone.hashCode());
        result = prime * result + ((grupoId == null) ? 0 : grupoId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Contato other = (Contato) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (telefone == null) {
            if (other.telefone != null)
                return false;
        } else if (!telefone.equals(other.telefone))
            return false;
        if (grupoId == null) {
            if (other.grupoId != null)
                return false;
        } else if (!grupoId.equals(other.grupoId))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Contato [id=" + id + ", nome=" + nome + ", telefone=" + telefone + ", grupoId=" + grupoId + "]";
    }

    

}
