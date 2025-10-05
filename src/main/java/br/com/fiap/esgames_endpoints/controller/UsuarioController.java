package br.com.fiap.esgames_endpoints.controller;

import br.com.fiap.esgames_endpoints.dto.UsuarioExibirDto;
import br.com.fiap.esgames_endpoints.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios") // ✅ padronizado no plural
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // ✅ LISTAR TODOS
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<UsuarioExibirDto> listarTodosUsuarios(Pageable paginacao) {
        return usuarioService.listarTodosUsuarios(paginacao);
    }

    // ✅ BUSCAR POR NOME
    @GetMapping(params = "nome")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioExibirDto buscarPorNome(@RequestParam String nome) {
        return usuarioService.buscarPorNome(nome);
    }

    // ✅ BUSCAR POR ID (agora é String)
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioExibirDto buscarPorId(@PathVariable String id) {
        return usuarioService.buscarPorId(id);
    }
}