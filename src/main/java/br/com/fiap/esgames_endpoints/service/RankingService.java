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
     */
    public List<RankingSetorDto> listarRankingPorSetor() {
        return rankingRepository.buscarRankingPorSetor()
                .stream()
                .map(obj -> new RankingSetorDto(
                        (String) obj.get("setor"),
                        ((Number) obj.get("pontosTotais")).longValue()
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
                        (String) obj.get("nomeUsuario"),
                        ((Number) obj.get("pontos")).longValue(),
                        (String) obj.get("setor")
                ))
                .collect(Collectors.toList());
    }

    /**
     * Registra uma nova atividade validando os campos obrigatórios.
     */
    public RegistroAtividade registrarAtividade(RegistroAtividadeRequestDto dto) {

        // ✅ Validação de campos obrigatórios
        if (dto.getIdUsuario() == null || dto.getIdUsuario().isBlank()) {
            throw new IllegalArgumentException("O campo 'idUsuario' é obrigatório.");
        }

        if (dto.getIdMissao() == null || dto.getIdMissao().isBlank()) {
            throw new IllegalArgumentException("O campo 'idMissao' é obrigatório.");
        }

        if (dto.getQuantidade() == null || dto.getQuantidade() <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }

        if (dto.getPontosGerados() == null || dto.getPontosGerados() <= 0) {
            throw new IllegalArgumentException("Os pontos gerados devem ser maiores que zero.");
        }

        // ✅ Criação do registro
        RegistroAtividade registro = new RegistroAtividade();
        registro.setUsuarioId(dto.getIdUsuario());
        registro.setMissaoId(dto.getIdMissao());
        registro.setRepresentanteId(dto.getIdRepresentante());
        registro.setMaterialId(dto.getIdMaterial());
        registro.setQuantidade(dto.getQuantidade());
        registro.setPontosGerados(dto.getPontosGerados());
        registro.setDataRegistro(dto.getDataRegistro() != null ? dto.getDataRegistro() : LocalDate.now());

        // ✅ Persistência
        return rankingRepository.save(registro);
    }
}