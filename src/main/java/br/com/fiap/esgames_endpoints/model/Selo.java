package br.com.fiap.esgames_endpoints.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "selos")
public class Selo {

    @Id
    private String id;
    private String nome;
    private String cor;
    private Integer pontuacaoMinima;

    public Selo() {
    }

    public Selo(String id, String nome, String cor, Integer pontuacaoMinima) {
        this.id = id;
        this.nome = nome;
        this.cor = cor;
        this.pontuacaoMinima = pontuacaoMinima;
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

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Integer getPontuacaoMinima() {
        return pontuacaoMinima;
    }

    public void setPontuacaoMinima(Integer pontuacaoMinima) {
        this.pontuacaoMinima = pontuacaoMinima;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Selo selo = (Selo) o;

        if (id != null ? !id.equals(selo.id) : selo.id != null) return false;
        if (nome != null ? !nome.equals(selo.nome) : selo.nome != null) return false;
        if (cor != null ? !cor.equals(selo.cor) : selo.cor != null) return false;
        return pontuacaoMinima != null ? pontuacaoMinima.equals(selo.pontuacaoMinima) : selo.pontuacaoMinima == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (cor != null ? cor.hashCode() : 0);
        result = 31 * result + (pontuacaoMinima != null ? pontuacaoMinima.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Selo{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", cor='" + cor + '\'' +
                ", pontuacaoMinima=" + pontuacaoMinima +
                '}';
    }
}