package br.com.fiap.esgames_endpoints.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "relatorios_mensais")
public class RelatorioMensal {

    @Id
    private String id;

    private LocalDate mesReferencia;
    private LocalDate anoReferencia;
    private Integer totalPontos;
    private Integer totalDoacoes;
    private Integer totalReciclados;
    private String usuarioId;

    public RelatorioMensal() {
    }

    public RelatorioMensal(String id, LocalDate mesReferencia, LocalDate anoReferencia,
                           Integer totalPontos, Integer totalDoacoes, Integer totalReciclados,
                           String usuarioId) {
        this.id = id;
        this.mesReferencia = mesReferencia;
        this.anoReferencia = anoReferencia;
        this.totalPontos = totalPontos;
        this.totalDoacoes = totalDoacoes;
        this.totalReciclados = totalReciclados;
        this.usuarioId = usuarioId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getMesReferencia() {
        return mesReferencia;
    }

    public void setMesReferencia(LocalDate mesReferencia) {
        this.mesReferencia = mesReferencia;
    }

    public LocalDate getAnoReferencia() {
        return anoReferencia;
    }

    public void setAnoReferencia(LocalDate anoReferencia) {
        this.anoReferencia = anoReferencia;
    }

    public Integer getTotalPontos() {
        return totalPontos;
    }

    public void setTotalPontos(Integer totalPontos) {
        this.totalPontos = totalPontos;
    }

    public Integer getTotalDoacoes() {
        return totalDoacoes;
    }

    public void setTotalDoacoes(Integer totalDoacoes) {
        this.totalDoacoes = totalDoacoes;
    }

    public Integer getTotalReciclados() {
        return totalReciclados;
    }

    public void setTotalReciclados(Integer totalReciclados) {
        this.totalReciclados = totalReciclados;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RelatorioMensal that = (RelatorioMensal) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (mesReferencia != null ? !mesReferencia.equals(that.mesReferencia) : that.mesReferencia != null)
            return false;
        if (anoReferencia != null ? !anoReferencia.equals(that.anoReferencia) : that.anoReferencia != null)
            return false;
        if (totalPontos != null ? !totalPontos.equals(that.totalPontos) : that.totalPontos != null) return false;
        if (totalDoacoes != null ? !totalDoacoes.equals(that.totalDoacoes) : that.totalDoacoes != null) return false;
        if (totalReciclados != null ? !totalReciclados.equals(that.totalReciclados) : that.totalReciclados != null)
            return false;
        return usuarioId != null ? usuarioId.equals(that.usuarioId) : that.usuarioId == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (mesReferencia != null ? mesReferencia.hashCode() : 0);
        result = 31 * result + (anoReferencia != null ? anoReferencia.hashCode() : 0);
        result = 31 * result + (totalPontos != null ? totalPontos.hashCode() : 0);
        result = 31 * result + (totalDoacoes != null ? totalDoacoes.hashCode() : 0);
        result = 31 * result + (totalReciclados != null ? totalReciclados.hashCode() : 0);
        result = 31 * result + (usuarioId != null ? usuarioId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RelatorioMensal{" +
                "id='" + id + '\'' +
                ", mesReferencia=" + mesReferencia +
                ", anoReferencia=" + anoReferencia +
                ", totalPontos=" + totalPontos +
                ", totalDoacoes=" + totalDoacoes +
                ", totalReciclados=" + totalReciclados +
                ", usuarioId='" + usuarioId + '\'' +
                '}';
    }
}