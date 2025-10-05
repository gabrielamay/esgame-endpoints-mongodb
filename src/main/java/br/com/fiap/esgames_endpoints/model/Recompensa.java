package br.com.fiap.esgames_endpoints.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "recompensas")
public class Recompensa {

    @Id
    private String id;
    private String nome;
    private String descricao;
    private Integer pontosNecessarios;
    private Integer estoque;
    private String imagem;

    public Recompensa() {
    }

    public Recompensa(String id, String nome, String descricao, Integer pontosNecessarios, Integer estoque, String imagem) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.pontosNecessarios = pontosNecessarios;
        this.estoque = estoque;
        this.imagem = imagem;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getPontosNecessarios() {
        return pontosNecessarios;
    }

    public void setPontosNecessarios(Integer pontosNecessarios) {
        this.pontosNecessarios = pontosNecessarios;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Recompensa that = (Recompensa) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (nome != null ? !nome.equals(that.nome) : that.nome != null) return false;
        if (descricao != null ? !descricao.equals(that.descricao) : that.descricao != null) return false;
        if (pontosNecessarios != null ? !pontosNecessarios.equals(that.pontosNecessarios) : that.pontosNecessarios != null)
            return false;
        if (estoque != null ? !estoque.equals(that.estoque) : that.estoque != null) return false;
        return imagem != null ? imagem.equals(that.imagem) : that.imagem == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (descricao != null ? descricao.hashCode() : 0);
        result = 31 * result + (pontosNecessarios != null ? pontosNecessarios.hashCode() : 0);
        result = 31 * result + (estoque != null ? estoque.hashCode() : 0);
        result = 31 * result + (imagem != null ? imagem.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Recompensa{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", pontosNecessarios=" + pontosNecessarios +
                ", estoque=" + estoque +
                ", imagem='" + imagem + '\'' +
                '}';
    }
}
