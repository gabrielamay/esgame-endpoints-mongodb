package br.com.fiap.esgames_endpoints.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "materiais")
public class Material {

    @Id
    private String id;
    private String nome;
    private String unidade;
    private Integer pontosPorUnidade;

    public Material() {
    }

    public Material(String id, String nome, String unidade, Integer pontosPorUnidade) {
        this.id = id;
        this.nome = nome;
        this.unidade = unidade;
        this.pontosPorUnidade = pontosPorUnidade;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public Integer getPontosPorUnidade() {
        return pontosPorUnidade;
    }

    public void setPontosPorUnidade(Integer pontosPorUnidade) {
        this.pontosPorUnidade = pontosPorUnidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Material material = (Material) o;

        if (id != null ? !id.equals(material.id) : material.id != null) return false;
        if (nome != null ? !nome.equals(material.nome) : material.nome != null) return false;
        if (unidade != null ? !unidade.equals(material.unidade) : material.unidade != null) return false;
        return pontosPorUnidade != null ? pontosPorUnidade.equals(material.pontosPorUnidade) : material.pontosPorUnidade == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (unidade != null ? unidade.hashCode() : 0);
        result = 31 * result + (pontosPorUnidade != null ? pontosPorUnidade.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Material{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", unidade='" + unidade + '\'' +
                ", pontosPorUnidade=" + pontosPorUnidade +
                '}';
    }
}
