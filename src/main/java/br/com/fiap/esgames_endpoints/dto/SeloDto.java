package br.com.fiap.esgames_endpoints.dto;

import br.com.fiap.esgames_endpoints.model.Selo;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SeloDto(
        @NotBlank(message = "O nome do selo é obrigatório")
        String nome,

        @NotBlank(message = "A cor do selo é obrigatória")
        String cor,

        @NotNull(message = "A pontuação mínima do selo é obrigatória")
        @Min(value = 0, message = "A pontuação mínima deve ser zero ou maior")
        Integer pontuacaoMinima
) {
    public Selo toDomain() {
        Selo selo = new Selo();
        selo.setNome(this.nome);
        selo.setCor(this.cor);
        selo.setPontuacaoMinima(this.pontuacaoMinima);
        return selo;
    }
}
