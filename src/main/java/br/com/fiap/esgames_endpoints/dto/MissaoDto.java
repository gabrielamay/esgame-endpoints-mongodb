package br.com.fiap.esgames_endpoints.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class MissaoDto {
    @NotBlank(message = "O nome da missão é obrigatório")
    @Size(max = 100, message = "O nome da missão deve ter no máximo 100 caracteres")
    private String nome;

    @NotBlank(message = "A descrição da missão é obrigatória")
    @Size(max = 1000, message = "A descrição da missão deve ter no máximo 1000 caracteres")
    private String descricao;

    @NotNull(message = "A data de início da missão é obrigatória")
    @Future(message = "A data de início deve ser uma data futura")
    private LocalDate dataInicio;

    @NotNull(message = "A data de fim da missão é obrigatória")
    @Future(message = "A data de fim deve ser uma data futura")
    private LocalDate dataFim;

    @NotNull(message = "Os pontos base da missão são obrigatórios")
    private Integer pontosBase;

    @NotBlank(message = "O tipo de material é obrigatório")
    @Size(max = 255, message = "O tipo de material deve ter no máximo 255 caracteres")
    private String tipoMaterial;

    public MissaoDto(String nome, String descricao, LocalDate dataInicio, LocalDate dataFim, Integer pontosBase, String tipoMaterial) {
        this.nome = nome;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.pontosBase = pontosBase;
        this.tipoMaterial = tipoMaterial;
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

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public Integer getPontosBase() {
        return pontosBase;
    }

    public void setPontosBase(Integer pontosBase) {
        this.pontosBase = pontosBase;
    }

    public String getTipoMaterial() {
        return tipoMaterial;
    }

    public void setTipoMaterial(String tipoMaterial) {
        this.tipoMaterial = tipoMaterial;
    }
}

