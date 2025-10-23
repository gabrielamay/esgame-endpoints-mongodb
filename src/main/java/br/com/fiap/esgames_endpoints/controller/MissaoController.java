package br.com.fiap.esgames_endpoints.controller;

import br.com.fiap.esgames_endpoints.dto.MissaoDto;
import br.com.fiap.esgames_endpoints.exception.MissaoJaExistenteException;
import br.com.fiap.esgames_endpoints.service.MissaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/missoes")
@Tag(name = "Missões", description = "Endpoints para gerenciamento de missões do sistema ESGames")
public class MissaoController {

    private final MissaoService missaoService;

    @Autowired
    public MissaoController(MissaoService missaoService) {
        this.missaoService = missaoService;
    }

    // ============================================================
    // ✅ LISTAR TODAS
    // ============================================================
    @Operation(summary = "Listar todas as missões", description = "Retorna uma lista com todas as missões cadastradas no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de missões retornada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MissaoDto.class))),
            @ApiResponse(responseCode = "204", description = "Nenhuma missão encontrada", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<MissaoDto>> listarMissoes() {
        List<MissaoDto> missoes = missaoService.listarMissoes();
        if (missoes == null || missoes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(missoes);
    }

    // ============================================================
    // ✅ CRIAR NOVA MISSÃO
    // ============================================================
    @Operation(summary = "Criar nova missão", description = "Cria uma nova missão no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Missão criada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MissaoDto.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos", content = @Content),
            @ApiResponse(responseCode = "409", description = "Missão duplicada", content = @Content)
    })
    @PostMapping
    public ResponseEntity<MissaoDto> criarMissao(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Dados da missão a ser criada", required = true)
            @Valid @RequestBody MissaoDto missaoDto) {
        
        MissaoDto novaMissao = missaoService.criarMissao(missaoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaMissao);
    }

    // ============================================================
    // ✅ ATUALIZAR MISSÃO
    // ============================================================
    @Operation(summary = "Atualizar missão existente", description = "Atualiza os dados de uma missão existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missão atualizada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MissaoDto.class))),
            @ApiResponse(responseCode = "404", description = "Missão não encontrada", content = @Content),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<MissaoDto> atualizarMissao(
            @Parameter(description = "ID da missão a ser atualizada", required = true)
            @PathVariable String id,
            @Valid @RequestBody MissaoDto missaoDto) {

        try {
            MissaoDto missaoAtualizada = missaoService.atualizarMissao(id, missaoDto);
            return ResponseEntity.ok(missaoAtualizada);

        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());

        } catch (RuntimeException e) {
            if (e.getMessage().contains("não encontrada")) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
            }
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    // ============================================================
    // ✅ DELETAR MISSÃO
    // ============================================================
    @Operation(summary = "Deletar missão", description = "Remove uma missão do sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Missão deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Missão não encontrada", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMissao(
            @Parameter(description = "ID da missão a ser deletada", required = true)
            @PathVariable String id) {

        try {
            missaoService.deletarMissao(id);
            return ResponseEntity.noContent().build();

        } catch (RuntimeException e) {
            if (e.getMessage().contains("não encontrada")) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
            }
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}