package br.com.fiap.esgames_endpoints.service;

import br.com.fiap.esgames_endpoints.dto.SeloDto;
import br.com.fiap.esgames_endpoints.exception.SeloJaExistenteException;
import br.com.fiap.esgames_endpoints.model.Selo;
import br.com.fiap.esgames_endpoints.repository.SeloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeloService {

    private final SeloRepository seloRepository;

    @Autowired
    public SeloService(SeloRepository seloRepository) {
        this.seloRepository = seloRepository;
    }

    // ============================================================
    // ✅ LISTAR
    // ============================================================
    public List<SeloDto> listarSelos() {
        return seloRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // ============================================================
    // ✅ BUSCAR POR ID
    // ============================================================
    public SeloDto buscarPorId(String id) {
        Selo selo = seloRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Selo não encontrado com id: " + id));

        return toDto(selo);
    }

    // ============================================================
    // ✅ CRIAR SELO
    // ============================================================
    public SeloDto criarSelo(SeloDto seloDto) {
        try {
            // Valida campos obrigatórios
            if (seloDto.nome() == null || seloDto.nome().isBlank()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O campo 'nome' é obrigatório.");
            }
            if (seloDto.cor() == null || seloDto.cor().isBlank()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O campo 'cor' é obrigatório.");
            }
            if (seloDto.pontuacaoMinima() == null || seloDto.pontuacaoMinima() < 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A pontuação mínima deve ser zero ou maior.");
            }

            // Valida duplicidade
            boolean existe = seloRepository.existsByNomeIgnoreCase(seloDto.nome());
            if (existe) {
                throw new SeloJaExistenteException("Já existe um selo com o nome: " + seloDto.nome());
            }

            // Salva no banco
            Selo novoSelo = seloDto.toDomain();
            Selo salvo = seloRepository.save(novoSelo);

            return toDto(salvo);

        } catch (SeloJaExistenteException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());

        } catch (ResponseStatusException e) {
            throw e;

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Erro ao criar selo: " + e.getMessage());
        }
    }

    // ============================================================
    // ✅ ATUALIZAR SELO
    // ============================================================
    public SeloDto atualizarSelo(String id, SeloDto seloDto) {
        Selo seloExistente = seloRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Selo não encontrado com id: " + id));

        seloExistente.setNome(seloDto.nome());
        seloExistente.setCor(seloDto.cor());
        seloExistente.setPontuacaoMinima(seloDto.pontuacaoMinima());

        Selo atualizado = seloRepository.save(seloExistente);
        return toDto(atualizado);
    }

    // ============================================================
    // ✅ DELETAR SELO
    // ============================================================
    public void deletarSelo(String id) {
        if (!seloRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Selo não encontrado com id: " + id);
        }
        seloRepository.deleteById(id);
    }

    // ============================================================
    // ✅ CONVERSORES
    // ============================================================
    private SeloDto toDto(Selo selo) {
        return new SeloDto(
                selo.getNome(),
                selo.getCor(),
                selo.getPontuacaoMinima()
        );
    }
}