package br.com.fiap.esgames_endpoints.controller;

import br.com.fiap.esgames_endpoints.dto.SeloDto;
import br.com.fiap.esgames_endpoints.model.Selo;
import br.com.fiap.esgames_endpoints.service.SeloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/selos")
public class SeloController {

    private final SeloService seloService;

    @Autowired
    public SeloController(SeloService seloService) {
        this.seloService = seloService;
    }

    @GetMapping
    public ResponseEntity<List<Selo>> listarSelos() {
        return ResponseEntity.ok(seloService.listarSelos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Selo> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(seloService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Selo> criarSelo(@RequestBody SeloDto selo) {
        return ResponseEntity.ok(seloService.criarSelo(selo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Selo> atualizarSelo(@PathVariable Long id, @RequestBody Selo selo) {
        return ResponseEntity.ok(seloService.atualizarSelo(id, selo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarSelo(@PathVariable Long id) {
        seloService.deletarSelo(id);
        return ResponseEntity.noContent().build();
    }
}
