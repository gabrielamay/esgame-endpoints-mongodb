package br.com.fiap.esgames_endpoints.controller;

import br.com.fiap.esgames_endpoints.dto.UsuarioExibirDto;
import br.com.fiap.esgames_endpoints.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios") // ✅ padronizado no plural
@Tag(name = "Usuários", description = "Endpoints para gerenciamento de usuários do sistema ESGames")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Operation(summary = "Listar todos os usuários", description = "Retorna uma lista paginada de todos os usuários cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de usuários retornada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class)))
    })
    // ✅ LISTAR TODOS
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<UsuarioExibirDto> listarTodosUsuarios(
            @Parameter(description = "Configuração de paginação (page, size, sort)")
            Pageable paginacao) {
        return usuarioService.listarTodosUsuarios(paginacao);
    }

    @Operation(summary = "Buscar usuário por nome", description = "Busca um usuário específico pelo nome")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioExibirDto.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content)
    })
    // ✅ BUSCAR POR NOME
    @GetMapping(params = "nome")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioExibirDto buscarPorNome(
            @Parameter(description = "Nome do usuário a ser buscado", required = true)
            @RequestParam String nome) {
        return usuarioService.buscarPorNome(nome);
    }

    @Operation(summary = "Buscar usuário por ID", description = "Busca um usuário específico pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioExibirDto.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content)
    })
    // ✅ BUSCAR POR ID (agora é String)
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioExibirDto buscarPorId(
            @Parameter(description = "ID do usuário a ser buscado", required = true)
            @PathVariable String id) {
        return usuarioService.buscarPorId(id);
    }
}