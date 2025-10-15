package br.com.fiap.esgames_endpoints.controller;

import br.com.fiap.esgames_endpoints.dto.SeloDto;
import br.com.fiap.esgames_endpoints.model.Selo;
import br.com.fiap.esgames_endpoints.service.SeloService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @Operation(summary = "Listar todos os selos", description = "Retorna uma lista com todos os selos cadastrados no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de selos retornada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Selo.class)))
    })
    @GetMapping
    public ResponseEntity<List<Selo>> listarSelos() {
        return ResponseEntity.ok(seloService.listarSelos());
    }

    @Operation(summary = "Buscar selo por ID", description = "Busca um selo específico pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Selo encontrado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Selo.class))),
            @ApiResponse(responseCode = "404", description = "Selo não encontrado", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Selo> buscarPorId(
            @Parameter(description = "ID do selo a ser buscado", required = true)
            @PathVariable String id) {
        return ResponseEntity.ok(seloService.buscarPorId(id));
    }

    @Operation(summary = "Criar novo selo", description = "Cria um novo selo no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Selo criado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Selo.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Selo> criarSelo(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Dados do selo a ser criado", required = true)
            @RequestBody SeloDto selo) {
        return ResponseEntity.ok(seloService.criarSelo(selo));
    }

    @Operation(summary = "Atualizar selo existente", description = "Atualiza os dados de um selo existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Selo atualizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Selo.class))),
            @ApiResponse(responseCode = "404", description = "Selo não encontrado", content = @Content),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Selo> atualizarSelo(
            @Parameter(description = "ID do selo a ser atualizado", required = true)
            @PathVariable String id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Novos dados do selo", required = true)
            @RequestBody Selo selo) {
        return ResponseEntity.ok(seloService.atualizarSelo(id, selo));
    }

    @Operation(summary = "Deletar selo", description = "Remove um selo do sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Selo deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Selo não encontrado", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarSelo(
            @Parameter(description = "ID do selo a ser deletado", required = true)
            @PathVariable String id) {
        seloService.deletarSelo(id);
        return ResponseEntity.noContent().build();
    }
}