package br.com.fiap.esgames_endpoints.controller;

import br.com.fiap.esgames_endpoints.dto.RankingIndividualDto;
import br.com.fiap.esgames_endpoints.dto.RankingSetorDto;
import br.com.fiap.esgames_endpoints.dto.RegistroAtividadeRequestDto;
import br.com.fiap.esgames_endpoints.service.RankingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ranking")
public class RankingController {

    private final RankingService rankingService;

    public RankingController(RankingService rankingService) {
        this.rankingService = rankingService;
    }

    @GetMapping("/setor")
    public ResponseEntity<List<RankingSetorDto>> listarRankingPorSetor() {
        List<RankingSetorDto> rankingSetor = rankingService.listarRankingPorSetor();
        return ResponseEntity.ok(rankingSetor);
    }

    @GetMapping("/individual")
    public ResponseEntity<List<RankingIndividualDto>> listarRankingIndividual() {
        List<RankingIndividualDto> rankingIndividual = rankingService.listarRankingIndividual();
        return ResponseEntity.ok(rankingIndividual);
    }

    @PostMapping("/registro-atividade")
    public ResponseEntity<String> registrarAtividade(@RequestBody RegistroAtividadeRequestDto registroDto) {
        rankingService.registrarAtividade(registroDto);
        return ResponseEntity.ok("Atividade registrada com sucesso!");
    }

}
