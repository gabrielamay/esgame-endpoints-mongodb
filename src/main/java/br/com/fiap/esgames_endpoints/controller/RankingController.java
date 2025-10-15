package br.com.fiap.esgames_endpoints.controller;

import br.com.fiap.esgames_endpoints.dto.RankingIndividualDto;
import br.com.fiap.esgames_endpoints.dto.RankingSetorDto;
import br.com.fiap.esgames_endpoints.dto.RegistroAtividadeRequestDto;
import br.com.fiap.esgames_endpoints.service.RankingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ranking")
@Tag(name = "Ranking", description = "Endpoints para consulta e gerenciamento de rankings do sistema ESGames")
public class RankingController {

    private final RankingService rankingService;

    public RankingController(RankingService rankingService) {
        this.rankingService = rankingService;
    }

    @Operation(summary = "Listar ranking por setor", description = "Retorna o ranking de pontuação agrupado por setor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ranking por setor retornado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RankingSetorDto.class)))
    })
    @GetMapping("/setor")
    public ResponseEntity<List<RankingSetorDto>> listarRankingPorSetor() {
        List<RankingSetorDto> rankingSetor = rankingService.listarRankingPorSetor();
        return ResponseEntity.ok(rankingSetor);
    }

    @Operation(summary = "Listar ranking individual", description = "Retorna o ranking individual dos usuários ordenado por pontuação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ranking individual retornado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RankingIndividualDto.class)))
    })
    @GetMapping("/individual")
    public ResponseEntity<List<RankingIndividualDto>> listarRankingIndividual() {
        List<RankingIndividualDto> rankingIndividual = rankingService.listarRankingIndividual();
        return ResponseEntity.ok(rankingIndividual);
    }

    @Operation(summary = "Registrar atividade", description = "Registra uma nova atividade realizada por um usuário e atualiza sua pontuação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atividade registrada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos", content = @Content),
            @ApiResponse(responseCode = "404", description = "Usuário ou missão não encontrados", content = @Content)
    })
    @PostMapping("/registro-atividade")
    public ResponseEntity<String> registrarAtividade(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Dados da atividade a ser registrada", required = true)
            @RequestBody RegistroAtividadeRequestDto registroDto) {
        rankingService.registrarAtividade(registroDto);
        return ResponseEntity.ok("Atividade registrada com sucesso!");
    }

}
