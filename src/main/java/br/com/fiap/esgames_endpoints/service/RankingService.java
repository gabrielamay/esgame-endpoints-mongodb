package br.com.fiap.esgames_endpoints.service;

import br.com.fiap.esgames_endpoints.dto.RankingIndividualDto;
import br.com.fiap.esgames_endpoints.dto.RankingSetorDto;
import br.com.fiap.esgames_endpoints.dto.RegistroAtividadeRequestDto;
import br.com.fiap.esgames_endpoints.model.*;
import br.com.fiap.esgames_endpoints.repository.RankingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import java.util.stream.Collectors;

@Service
public class RankingService {

    private final RankingRepository rankingRepository;

    public RankingService(RankingRepository rankingRepository) {
        this.rankingRepository = rankingRepository;
    }

    public List<RankingSetorDto> listarRankingPorSetor() {
        return rankingRepository.buscarRankingPorSetor()
                .stream()
                .map(obj -> new RankingSetorDto(
                        (String) obj[0],             // setor
                        ((Number) obj[1]).longValue() // pontos
                ))
                .collect(Collectors.toList());
    }

    public List<RankingIndividualDto> listarRankingIndividual() {
        return rankingRepository.buscarRankingIndividual()
                .stream()
                .map(obj -> new RankingIndividualDto(
                        (String) obj[0],              // nomeUsuario
                        ((Number) obj[1]).longValue(), // pontos
                        (String) obj[2]               // setor
                ))
                .collect(Collectors.toList());
    }

    public void registrarAtividade(RegistroAtividadeRequestDto dto) {
        RegistroAtividade registro = new RegistroAtividade();

        Usuario usuario = new Usuario();
        usuario.setIdUsuario(dto.getIdUsuario());
        registro.setUsuario(usuario);

        Missao missao = new Missao();
        missao.setId(dto.getIdMissao());
        registro.setMissao(missao);

        Representante representante = new Representante();
        representante.setId(dto.getIdRepresentante());
        registro.setRepresentante(representante);

        Material material = new Material();
        material.setId(dto.getIdMaterial());
        registro.setMaterial(material);

        registro.setQuantidade(dto.getQuantidade());
        registro.setPontosGerados(dto.getPontosGerados());
        registro.setDataRegistro(dto.getDataRegistro() != null ? dto.getDataRegistro() : LocalDate.now());

        rankingRepository.save(registro);
    }
}

