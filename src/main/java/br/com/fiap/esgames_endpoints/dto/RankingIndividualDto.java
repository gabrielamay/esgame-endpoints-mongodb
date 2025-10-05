package br.com.fiap.esgames_endpoints.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RankingIndividualDto {
    @NotBlank(message = "O nome do usuário é obrigatório")
    @Size(max = 100, message = "O nome do usuário deve ter no máximo 100 caracteres")
    private String nomeUsuario;

    @NotNull(message = "Os pontos do usuário são obrigatórios")
    private Long pontos;

    @NotBlank(message = "O setor do usuário é obrigatório")
    @Size(max = 100, message = "O setor do usuário deve ter no máximo 100 caracteres")
    private String setor;

    public RankingIndividualDto(String nomeUsuario, Long pontos, String setor) {
        this.nomeUsuario = nomeUsuario;
        this.pontos = pontos;
        this.setor = setor;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public Long getPontos() {
        return pontos;
    }

    public void setPontos(Long pontos) {
        this.pontos = pontos;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }
}

