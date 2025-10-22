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
import jakarta.validation.Valid; // ✅ Import para ativar as validações do DTO
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ranking")
@Tag(name = "Ranking", description = "Endpoints para consulta e gerenciamento de rankings do sistema ESGames")
public class RankingController {

    private final RankingService rankingService;

    @Autowired
    public RankingController(RankingService rankingService) {
        this.rankingService = rankingService;
    }

    // ============================================================
    // ✅ RANKING POR SETOR
    // ============================================================
    @Operation(summary = "Listar ranking por setor", description = "Retorna o ranking de pontuação agrupado por setor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ranking por setor retornado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RankingSetorDto.class))),
            @ApiResponse(responseCode = "204", description = "Nenhum dado encontrado", content = @Content)
    })
    @GetMapping("/setor")
    public ResponseEntity<List<RankingSetorDto>> listarRankingPorSetor() {
        List<RankingSetorDto> rankingSetor = rankingService.listarRankingPorSetor();

        if (rankingSetor == null || rankingSetor.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(rankingSetor);
    }

    // ============================================================
    // ✅ RANKING INDIVIDUAL
    // ============================================================
    @Operation(summary = "Listar ranking individual", description = "Retorna o ranking individual dos usuários ordenado por pontuação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ranking individual retornado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RankingIndividualDto.class))),
            @ApiResponse(responseCode = "204", description = "Nenhum dado encontrado", content = @Content)
    })
    @GetMapping("/individual")
    public ResponseEntity<List<RankingIndividualDto>> listarRankingIndividual() {
        List<RankingIndividualDto> rankingIndividual = rankingService.listarRankingIndividual();

        if (rankingIndividual == null || rankingIndividual.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(rankingIndividual);
    }

    // ============================================================
    // ✅ REGISTRAR ATIVIDADE
    // ============================================================
    @Operation(summary = "Registrar atividade", description = "Registra uma nova atividade realizada por um usuário e atualiza sua pontuação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Atividade registrada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor", content = @Content)
    })
    @PostMapping("/registro-atividade")
    public ResponseEntity<String> registrarAtividade(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Dados da atividade a ser registrada", required = true)
            @Valid @RequestBody RegistroAtividadeRequestDto registroDto) { // ✅ Ativa validação automática

        rankingService.registrarAtividade(registroDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Atividade registrada com sucesso!");
    }
}