package br.com.fiap.esgames_endpoints.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "usuarios_selos")
public class UsuarioSelo {

    @Id
    private String id;

    private String usuarioId;
    private String seloId;
    private LocalDate dataConquista;

    public UsuarioSelo() {
    }

    public UsuarioSelo(String id, String usuarioId, String seloId, LocalDate dataConquista) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.seloId = seloId;
        this.dataConquista = dataConquista;
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

    public String getSeloId() {
        return seloId;
    }

    public void setSeloId(String seloId) {
        this.seloId = seloId;
    }

    public LocalDate getDataConquista() {
        return dataConquista;
    }

    public void setDataConquista(LocalDate dataConquista) {
        this.dataConquista = dataConquista;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsuarioSelo that = (UsuarioSelo) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (usuarioId != null ? !usuarioId.equals(that.usuarioId) : that.usuarioId != null) return false;
        if (seloId != null ? !seloId.equals(that.seloId) : that.seloId != null) return false;
        return dataConquista != null ? dataConquista.equals(that.dataConquista) : that.dataConquista == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (usuarioId != null ? usuarioId.hashCode() : 0);
        result = 31 * result + (seloId != null ? seloId.hashCode() : 0);
        result = 31 * result + (dataConquista != null ? dataConquista.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UsuarioSelo{" +
                "id='" + id + '\'' +
                ", usuarioId='" + usuarioId + '\'' +
                ", seloId='" + seloId + '\'' +
                ", dataConquista=" + dataConquista +
                '}';
    }
}