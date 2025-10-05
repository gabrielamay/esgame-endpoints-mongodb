package br.com.fiap.esgames_endpoints.service;

import br.com.fiap.esgames_endpoints.dto.RankingIndividualDto;
import br.com.fiap.esgames_endpoints.dto.RankingSetorDto;
import br.com.fiap.esgames_endpoints.dto.RegistroAtividadeRequestDto;
import br.com.fiap.esgames_endpoints.model.RegistroAtividade;
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

    /**
     * Retorna o ranking agregado por setor.
     * Essa implementação assume que o método buscarRankingPorSetor() já retorna
     * um aggregation MongoDB (List<Map<String, Object>>).
     */
    public List<RankingSetorDto> listarRankingPorSetor() {
        return rankingRepository.buscarRankingPorSetor()
                .stream()
                .map(obj -> new RankingSetorDto(
                        (String) obj.get("setor"),                   // nome do setor
                        ((Number) obj.get("pontosTotais")).longValue() // soma de pontos
                ))
                .collect(Collectors.toList());
    }

    /**
     * Retorna o ranking individual de usuários.
     */
    public List<RankingIndividualDto> listarRankingIndividual() {
        return rankingRepository.buscarRankingIndividual()
                .stream()
                .map(obj -> new RankingIndividualDto(
                        (String) obj.get("nomeUsuario"),               // nome do usuário
                        ((Number) obj.get("pontos")).longValue(),      // total de pontos
                        (String) obj.get("setor")                      // setor
                ))
                .collect(Collectors.toList());
    }

    /**
     * Registra uma nova atividade, salvando diretamente no MongoDB.
     */
    public void registrarAtividade(RegistroAtividadeRequestDto dto) {
        RegistroAtividade registro = new RegistroAtividade();

        registro.setUsuarioId(dto.getIdUsuario());
        registro.setMissaoId(dto.getIdMissao());
        registro.setRepresentanteId(dto.getIdRepresentante());
        registro.setMaterialId(dto.getIdMaterial());
        registro.setQuantidade(dto.getQuantidade());
        registro.setPontosGerados(dto.getPontosGerados());
        registro.setDataRegistro(dto.getDataRegistro() != null ? dto.getDataRegistro() : LocalDate.now());

        rankingRepository.save(registro);
    }
}