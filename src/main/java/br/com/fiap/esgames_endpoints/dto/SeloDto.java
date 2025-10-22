package br.com.fiap.esgames_endpoints.dto;

import br.com.fiap.esgames_endpoints.model.Selo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "DTO para criação e atualização de Selos no sistema ESGames")
public record SeloDto(

        @Schema(description = "Nome do selo", example = "Selo Verde")
        @NotBlank(message = "O nome do selo é obrigatório")
        String nome,

        @Schema(description = "Cor representativa do selo (em formato hexadecimal)", example = "#00FF00")
        @NotBlank(message = "A cor do selo é obrigatória")
        String cor,

        @Schema(description = "Pontuação mínima necessária para obter o selo", example = "100")
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
