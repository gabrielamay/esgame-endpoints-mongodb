package br.com.fiap.esgames_endpoints.service;

import br.com.fiap.esgames_endpoints.dto.MissaoDto;
import br.com.fiap.esgames_endpoints.exception.MissaoJaExistenteException;
import br.com.fiap.esgames_endpoints.exception.MissaoNaoEncontradaException;
import br.com.fiap.esgames_endpoints.model.Missao;
import br.com.fiap.esgames_endpoints.repository.MissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;

@Service
public class MissaoService {

    private final MissaoRepository missaoRepository;

    @Autowired
    public MissaoService(MissaoRepository missaoRepository) {
        this.missaoRepository = missaoRepository;
    }

    // ============================================================
    // ✅ LISTAR
    // ============================================================
    public List<MissaoDto> listarMissoes() {
        return missaoRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // ============================================================
    // ✅ CRIAR
    // ============================================================
    public MissaoDto criarMissao(MissaoDto missaoDTO) {
        try {
            // 🔍 Verifica duplicidade
            if (missaoRepository.existsByNomeIgnoreCase(missaoDTO.getNome())) {
                throw new MissaoJaExistenteException(
                        "Já existe uma missão cadastrada com o nome: " + missaoDTO.getNome());
            }

            // 🧭 Valida coerência das datas
            if (missaoDTO.getDataFim().isBefore(missaoDTO.getDataInicio())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "A data de fim não pode ser anterior à data de início.");
            }

            // 🕒 Impede datas passadas
            if (missaoDTO.getDataInicio().isBefore(LocalDate.now())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "A data de início não pode ser no passado.");
            }

            Missao missao = toEntity(missaoDTO);
            Missao novaMissao = missaoRepository.save(missao);
            return toDto(novaMissao);

        } catch (MissaoJaExistenteException e) {
            // 409 CONFLICT
            throw new MissaoJaExistenteException(e.getMessage());

        } catch (ResponseStatusException e) {
            throw e; // já é tratado com o status correto

        } catch (Exception e) {
            // 500 INTERNAL SERVER ERROR — fallback
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Erro ao criar missão: " + e.getMessage());
        }
    }

    // ============================================================
    // ✅ ATUALIZAR
    // ============================================================
    public MissaoDto atualizarMissao(String id, MissaoDto missaoDto) {
        Missao missaoExistente = missaoRepository.findById(id)
                .orElseThrow(() -> new MissaoNaoEncontradaException("Missão não encontrada com id: " + id));

        if (missaoDto.getDataFim().isBefore(missaoDto.getDataInicio())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "A data de fim não pode ser anterior à data de início.");
        }

        missaoExistente.setNome(missaoDto.getNome());
        missaoExistente.setDescricao(missaoDto.getDescricao());
        missaoExistente.setDataInicio(missaoDto.getDataInicio());
        missaoExistente.setDataFim(missaoDto.getDataFim());
        missaoExistente.setPontosBase(missaoDto.getPontosBase());
        missaoExistente.setTipoMaterial(missaoDto.getTipoMaterial());

        Missao atualizada = missaoRepository.save(missaoExistente);
        return toDto(atualizada);
    }

    // ============================================================
    // ✅ DELETAR
    // ============================================================
    public void deletarMissao(String id) {
        if (!missaoRepository.existsById(id)) {
            throw new MissaoNaoEncontradaException("Missão não encontrada com id: " + id);
        }

        missaoRepository.deleteById(id);
    }

    // ============================================================
    // ✅ EXISTE POR NOME
    // ============================================================
    public boolean existeMissaoPorNome(String nome) {
        return missaoRepository.existsByNomeIgnoreCase(nome);
    }

    // ============================================================
    // ✅ CONVERSÕES
    // ============================================================
    private MissaoDto toDto(Missao missao) {
        return new MissaoDto(
                missao.getNome(),
                missao.getDescricao(),
                missao.getDataInicio(),
                missao.getDataFim(),
                missao.getPontosBase(),
                missao.getTipoMaterial()
        );
    }

    private Missao toEntity(MissaoDto dto) {
        Missao missao = new Missao();
        missao.setNome(dto.getNome());
        missao.setDescricao(dto.getDescricao());
        missao.setDataInicio(dto.getDataInicio());
        missao.setDataFim(dto.getDataFim());
        missao.setPontosBase(dto.getPontosBase());
        missao.setTipoMaterial(dto.getTipoMaterial());
        return missao;
    }
}