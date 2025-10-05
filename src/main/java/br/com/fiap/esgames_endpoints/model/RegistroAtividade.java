package br.com.fiap.esgames_endpoints.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "registro_atividade")
public class RegistroAtividade {

    @Id
    private String id;

    private String usuarioId;
    private String missaoId;
    private String representanteId;
    private String materialId;

    private Integer quantidade;
    private Integer pontosGerados;
    private LocalDate dataRegistro;

    public RegistroAtividade() {
    }

    public RegistroAtividade(String id, String usuarioId, String missaoId, String representanteId, String materialId,
                             Integer quantidade, Integer pontosGerados, LocalDate dataRegistro) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.missaoId = missaoId;
        this.representanteId = representanteId;
        this.materialId = materialId;
        this.quantidade = quantidade;
        this.pontosGerados = pontosGerados;
        this.dataRegistro = dataRegistro;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getMissaoId() {
        return missaoId;
    }

    public void setMissaoId(String missaoId) {
        this.missaoId = missaoId;
    }

    public String getRepresentanteId() {
        return representanteId;
    }

    public void setRepresentanteId(String representanteId) {
        this.representanteId = representanteId;
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Integer getPontosGerados() {
        return pontosGerados;
    }

    public void setPontosGerados(Integer pontosGerados) {
        this.pontosGerados = pontosGerados;
    }

    public LocalDate getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(LocalDate dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RegistroAtividade that = (RegistroAtividade) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (usuarioId != null ? !usuarioId.equals(that.usuarioId) : that.usuarioId != null) return false;
        if (missaoId != null ? !missaoId.equals(that.missaoId) : that.missaoId != null) return false;
        if (representanteId != null ? !representanteId.equals(that.representanteId) : that.representanteId != null)
            return false;
        if (materialId != null ? !materialId.equals(that.materialId) : that.materialId != null) return false;
        if (quantidade != null ? !quantidade.equals(that.quantidade) : that.quantidade != null) return false;
        if (pontosGerados != null ? !pontosGerados.equals(that.pontosGerados) : that.pontosGerados != null)
            return false;
        return dataRegistro != null ? dataRegistro.equals(that.dataRegistro) : that.dataRegistro == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (usuarioId != null ? usuarioId.hashCode() : 0);
        result = 31 * result + (missaoId != null ? missaoId.hashCode() : 0);
        result = 31 * result + (representanteId != null ? representanteId.hashCode() : 0);
        result = 31 * result + (materialId != null ? materialId.hashCode() : 0);
        result = 31 * result + (quantidade != null ? quantidade.hashCode() : 0);
        result = 31 * result + (pontosGerados != null ? pontosGerados.hashCode() : 0);
        result = 31 * result + (dataRegistro != null ? dataRegistro.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RegistroAtividade{" +
                "id='" + id + '\'' +
                ", usuarioId='" + usuarioId + '\'' +
                ", missaoId='" + missaoId + '\'' +
                ", representanteId='" + representanteId + '\'' +
                ", materialId='" + materialId + '\'' +
                ", quantidade=" + quantidade +
                ", pontosGerados=" + pontosGerados +
                ", dataRegistro=" + dataRegistro +
                '}';
    }
}