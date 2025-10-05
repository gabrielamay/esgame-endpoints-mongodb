package br.com.fiap.esgames_endpoints.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "usuarios_recompensas")
public class UsuarioRecompensa {

    @Id
    private String id;

    private String usuarioId;
    private String recompensaId;
    private LocalDate dataResgate;
    private String statusResgate;

    public UsuarioRecompensa() {
    }

    public UsuarioRecompensa(String id, String usuarioId, String recompensaId, LocalDate dataResgate, String statusResgate) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.recompensaId = recompensaId;
        this.dataResgate = dataResgate;
        this.statusResgate = statusResgate;
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

    public String getRecompensaId() {
        return recompensaId;
    }

    public void setRecompensaId(String recompensaId) {
        this.recompensaId = recompensaId;
    }

    public LocalDate getDataResgate() {
        return dataResgate;
    }

    public void setDataResgate(LocalDate dataResgate) {
        this.dataResgate = dataResgate;
    }

    public String getStatusResgate() {
        return statusResgate;
    }

    public void setStatusResgate(String statusResgate) {
        this.statusResgate = statusResgate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsuarioRecompensa that = (UsuarioRecompensa) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (usuarioId != null ? !usuarioId.equals(that.usuarioId) : that.usuarioId != null) return false;
        if (recompensaId != null ? !recompensaId.equals(that.recompensaId) : that.recompensaId != null) return false;
        if (dataResgate != null ? !dataResgate.equals(that.dataResgate) : that.dataResgate != null) return false;
        return statusResgate != null ? statusResgate.equals(that.statusResgate) : that.statusResgate == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (usuarioId != null ? usuarioId.hashCode() : 0);
        result = 31 * result + (recompensaId != null ? recompensaId.hashCode() : 0);
        result = 31 * result + (dataResgate != null ? dataResgate.hashCode() : 0);
        result = 31 * result + (statusResgate != null ? statusResgate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UsuarioRecompensa{" +
                "id='" + id + '\'' +
                ", usuarioId='" + usuarioId + '\'' +
                ", recompensaId='" + recompensaId + '\'' +
                ", dataResgate=" + dataResgate +
                ", statusResgate='" + statusResgate + '\'' +
                '}';
    }
}