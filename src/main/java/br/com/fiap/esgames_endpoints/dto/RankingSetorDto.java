package br.com.fiap.esgames_endpoints.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RankingSetorDto {
    @NotBlank(message = "O setor é obrigatório")
    @Size(max = 100, message = "O setor deve ter no máximo 100 caracteres")
    private String setor;
    @NotNull(message = "A pontuação total é obrigatória")
    private Long pontosTotais;

    public RankingSetorDto(String setor, Long pontosTotais) {
        this.setor = setor;
        this.pontosTotais = pontosTotais;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public Long getPontosTotais() {
        return pontosTotais;
    }

    public void setPontosTotais(Long pontosTotais) {
        this.pontosTotais = pontosTotais;
    }
}

