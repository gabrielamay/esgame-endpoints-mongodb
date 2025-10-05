package br.com.fiap.esgames_endpoints.controller;

import br.com.fiap.esgames_endpoints.dto.UsuarioExibirDto;
import br.com.fiap.esgames_endpoints.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/usuario")
    @ResponseStatus(HttpStatus.OK)
    public Page<UsuarioExibirDto> listarTodosUsuarios(Pageable paginacao) {
        return usuarioService.listarTodosUsuarios(paginacao);
    }

    @GetMapping(value = "/usuario", params = "nome")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioExibirDto buscarPorNome(@RequestParam String nome) {
        return usuarioService.buscarPorNome(nome);
    }

    @GetMapping(value = "/usuario", params = "id")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioExibirDto buscarPorId(@RequestParam Long id) {
        return usuarioService.buscarPorId(id);
    }
}
