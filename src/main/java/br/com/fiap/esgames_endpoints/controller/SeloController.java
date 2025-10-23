package br.com.fiap.esgames_endpoints.controller;

import br.com.fiap.esgames_endpoints.dto.SeloDto;
import br.com.fiap.esgames_endpoints.exception.SeloJaExistenteException;
import br.com.fiap.esgames_endpoints.service.SeloService;
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
@RequestMapping("/selos")
@Tag(name = "Selos", description = "Endpoints para gerenciamento de selos e conquistas do sistema ESGames")
public class SeloController {

    private final SeloService seloService;

    @Autowired
    public SeloController(SeloService seloService) {
        this.seloService = seloService;
    }

    // ============================================================
    // ✅ LISTAR TODOS
    // ============================================================
    @Operation(summary = "Listar todos os selos", description = "Retorna uma lista com todos os selos cadastrados no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de selos retornada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = SeloDto.class))),
            @ApiResponse(responseCode = "204", description = "Nenhum selo encontrado", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<SeloDto>> listarSelos() {
        try {
            List<SeloDto> selos = seloService.listarSelos();

            if (selos == null || selos.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(selos);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao listar selos: " + e.getMessage());
        }
    }

    // ============================================================
    // ✅ BUSCAR POR ID
    // ============================================================
    @Operation(summary = "Buscar selo por ID", description = "Busca um selo específico pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Selo encontrado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = SeloDto.class))),
            @ApiResponse(responseCode = "404", description = "Selo não encontrado", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<SeloDto> buscarPorId(
            @Parameter(description = "ID do selo a ser buscado", required = true)
            @PathVariable String id) {
        try {
            SeloDto selo = seloService.buscarPorId(id);
            if (selo == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Selo não encontrado.");
            }
            return ResponseEntity.ok(selo);
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao buscar selo: " + e.getMessage());
        }
    }

    // ============================================================
    // ✅ CRIAR NOVO SELO
    // ============================================================
    @Operation(summary = "Criar novo selo", description = "Cria um novo selo no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Selo criado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = SeloDto.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos", content = @Content),
            @ApiResponse(responseCode = "409", description = "Selo duplicado", content = @Content)
    })
    @PostMapping
    public ResponseEntity<SeloDto> criarSelo(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Dados do selo a ser criado", required = true)
            @Valid @RequestBody SeloDto seloDto) {
        
        SeloDto novoSelo = seloService.criarSelo(seloDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoSelo);
    }

    // ============================================================
    // ✅ ATUALIZAR SELO
    // ============================================================
    @Operation(summary = "Atualizar selo existente", description = "Atualiza os dados de um selo existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Selo atualizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = SeloDto.class))),
            @ApiResponse(responseCode = "404", description = "Selo não encontrado", content = @Content),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<SeloDto> atualizarSelo(
            @Parameter(description = "ID do selo a ser atualizado", required = true)
            @PathVariable String id,
            @Valid @RequestBody SeloDto seloDto) {

        try {
            SeloDto atualizado = seloService.atualizarSelo(id, seloDto);
            return ResponseEntity.ok(atualizado);

        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());

        } catch (RuntimeException e) {
            if (e.getMessage().contains("não encontrado")) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Selo não encontrado.");
            }
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    // ============================================================
    // ✅ DELETAR SELO
    // ============================================================
    @Operation(summary = "Deletar selo", description = "Remove um selo do sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Selo deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Selo não encontrado", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarSelo(
            @Parameter(description = "ID do selo a ser deletado", required = true)
            @PathVariable String id) {

        try {
            seloService.deletarSelo(id);
            return ResponseEntity.noContent().build();

        } catch (RuntimeException e) {
            if (e.getMessage().contains("não encontrado")) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Selo não encontrado.");
            }
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao deletar selo: " + e.getMessage());
        }
    }
}