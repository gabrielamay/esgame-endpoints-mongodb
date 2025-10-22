package br.com.fiap.esgames_endpoints.dto;

import br.com.fiap.esgames_endpoints.model.Missao;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Schema(description = "DTO utilizado para criação e atualização de missões ESG")
public class MissaoDto {

    @Schema(description = "Nome da missão", example = "Reduzir consumo de papel")
    @NotBlank(message = "O nome da missão é obrigatório")
    @Size(max = 100, message = "O nome da missão deve ter no máximo 100 caracteres")
    private String nome;

    @Schema(description = "Descrição detalhada da missão", example = "Incentivar uso digital e reduzir impressões desnecessárias")
    @NotBlank(message = "A descrição da missão é obrigatória")
    @Size(max = 1000, message = "A descrição da missão deve ter no máximo 1000 caracteres")
    private String descricao;

    @Schema(description = "Data de início da missão", example = "2025-10-21")
    @NotNull(message = "A data de início da missão é obrigatória")
    @FutureOrPresent(message = "A data de início deve ser hoje ou uma data futura")
    private LocalDate dataInicio;

    @Schema(description = "Data de término da missão", example = "2025-12-31")
    @NotNull(message = "A data de fim da missão é obrigatória")
    @Future(message = "A data de fim deve ser uma data futura")
    private LocalDate dataFim;

    @Schema(description = "Pontuação base da missão", example = "100")
    @NotNull(message = "Os pontos base da missão são obrigatórios")
    @Min(value = 0, message = "Os pontos base devem ser zero ou maiores")
    private Integer pontosBase;

    @Schema(description = "Tipo de material associado à missão", example = "Papel")
    @NotBlank(message = "O tipo de material é obrigatório")
    @Size(max = 255, message = "O tipo de material deve ter no máximo 255 caracteres")
    private String tipoMaterial;

    // ============================================================
    // ✅ Construtores
    // ============================================================
    public MissaoDto() {
    }

    public MissaoDto(String nome, String descricao, LocalDate dataInicio, LocalDate dataFim, Integer pontosBase, String tipoMaterial) {
        this.nome = nome;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.pontosBase = pontosBase;
        this.tipoMaterial = tipoMaterial;
    }

    // ============================================================
    // ✅ Conversão para domínio
    // ============================================================
    public Missao toDomain() {
        Missao missao = new Missao();
        missao.setNome(this.nome);
        missao.setDescricao(this.descricao);
        missao.setDataInicio(this.dataInicio);
        missao.setDataFim(this.dataFim);
        missao.setPontosBase(this.pontosBase);
        missao.setTipoMaterial(this.tipoMaterial);
        return missao;
    }

    // ============================================================
    // ✅ Getters e Setters
    // ============================================================
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

