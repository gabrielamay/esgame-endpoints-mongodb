package br.com.fiap.esgames_endpoints.controller;

import br.com.fiap.esgames_endpoints.dto.MissaoDto;
import br.com.fiap.esgames_endpoints.service.MissaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/missoes")
public class MissaoController {

    private final MissaoService missaoService;

    @Autowired
    public MissaoController(MissaoService missaoService) {
        this.missaoService = missaoService;
    }

    @GetMapping
    public ResponseEntity<List<MissaoDto>> listarMissoes() {
        List<MissaoDto> missoes = missaoService.listarMissoes();
        return ResponseEntity.ok(missoes);
    }

    @PostMapping
    public ResponseEntity<MissaoDto> criarMissao(@RequestBody MissaoDto missaoDto) {
        MissaoDto novaMissao = missaoService.criarMissao(missaoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaMissao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MissaoDto> atualizarMissao(@PathVariable String id, @RequestBody MissaoDto missaoDto) {
        MissaoDto missaoAtualizada = missaoService.atualizarMissao(id, missaoDto);
        return ResponseEntity.ok(missaoAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMissao(@PathVariable String id) {
        missaoService.deletarMissao(id);
        return ResponseEntity.noContent().build();
    }
}
