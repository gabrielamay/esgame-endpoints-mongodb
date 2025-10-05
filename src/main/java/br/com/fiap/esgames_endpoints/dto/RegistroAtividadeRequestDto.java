package br.com.fiap.esgames_endpoints.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public class RegistroAtividadeRequestDto {

    @NotBlank(message = "O ID do usuário é obrigatório")
    private String idUsuario;

    @NotBlank(message = "O ID da missão é obrigatório")
    private String idMissao;

    @NotBlank(message = "O ID do representante é obrigatório")
    private String idRepresentante;

    @NotBlank(message = "O ID do material é obrigatório")
    private String idMaterial;

    @NotNull(message = "A quantidade é obrigatória")
    private Integer quantidade;

    @NotNull(message = "Os pontos gerados são obrigatórios")
    private Integer pontosGerados;

    @PastOrPresent(message = "A data de registro deve ser no presente ou no passado")
    private LocalDate dataRegistro;

    // Getters e setters
    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdMissao() {
        return idMissao;
    }

    public void setIdMissao(String idMissao) {
        this.idMissao = idMissao;
    }

    public String getIdRepresentante() {
        return idRepresentante;
    }

    public void setIdRepresentante(String idRepresentante) {
        this.idRepresentante = idRepresentante;
    }

    public String getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(String idMaterial) {
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