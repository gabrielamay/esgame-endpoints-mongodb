package br.com.fiap.esgames_endpoints.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public class RegistroAtividadeRequestDto {

    @NotNull(message = "O ID do usuário é obrigatório")
    private Long idUsuario;

    @NotNull(message = "O ID da missão é obrigatório")
    private Long idMissao;

    @NotNull(message = "O ID do representante é obrigatório")
    private Long idRepresentante;

    @NotNull(message = "O ID do material é obrigatório")
    private Long idMaterial;

    @NotNull(message = "A quantidade é obrigatória")
    @Min(value = 1, message = "A quantidade deve ser no mínimo 1")
    private Integer quantidade;

    @NotNull(message = "Os pontos gerados são obrigatórios")
    @Min(value = 0, message = "Os pontos gerados não podem ser negativos")
    private Integer pontosGerados;

    @NotNull(message = "A data de registro é obrigatória")
    @PastOrPresent(message = "A data de registro deve ser no presente ou passado")
    private LocalDate dataRegistro;

    public RegistroAtividadeRequestDto() {}

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdMissao() {
        return idMissao;
    }

    public void setIdMissao(Long idMissao) {
        this.idMissao = idMissao;
    }

    public Long getIdRepresentante() {
        return idRepresentante;
    }

    public void setIdRepresentante(Long idRepresentante) {
        this.idRepresentante = idRepresentante;
    }

    public Long getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(Long idMaterial) {
        this.idMaterial = idMaterial;
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
}

