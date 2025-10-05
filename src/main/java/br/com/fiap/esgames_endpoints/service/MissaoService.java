package br.com.fiap.esgames_endpoints.service;

import br.com.fiap.esgames_endpoints.dto.MissaoDto;
import br.com.fiap.esgames_endpoints.exception.MissaoJaExistenteException;
import br.com.fiap.esgames_endpoints.exception.MissaoNaoEncontradaException;
import br.com.fiap.esgames_endpoints.model.Missao;
import br.com.fiap.esgames_endpoints.repository.MissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MissaoService {

    private final MissaoRepository missaoRepository;

    @Autowired
    public MissaoService(MissaoRepository missaoRepository) {
        this.missaoRepository = missaoRepository;
    }

    public List<MissaoDto> listarMissoes() {
        return missaoRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public MissaoDto criarMissao(MissaoDto missaoDTO) {
        // Verificar se já existe missão com mesmo nome
        boolean existeMissao = missaoRepository.existsByNomeIgnoreCase(missaoDTO.getNome());

        if (existeMissao) {
            throw new MissaoJaExistenteException("Já existe uma missão cadastrada com o nome: " + missaoDTO.getNome());
        }

        Missao missao = toEntity(missaoDTO);
        Missao novaMissao = missaoRepository.save(missao);
        return toDto(novaMissao);
    }

    public MissaoDto atualizarMissao(Long id, MissaoDto missaoDto) {
        Missao missaoExistente = missaoRepository.findById(id)
                .orElseThrow(() -> new MissaoNaoEncontradaException("Missão não encontrada com id: " + id));

        missaoExistente.setNome(missaoDto.getNome());
        missaoExistente.setDescricao(missaoDto.getDescricao());
        missaoExistente.setDataInicio(missaoDto.getDataInicio());
        missaoExistente.setDataFim(missaoDto.getDataFim());
        missaoExistente.setPontosBase(missaoDto.getPontosBase());
        missaoExistente.setTipoMaterial(missaoDto.getTipoMaterial());

        Missao missaoAtualizada = missaoRepository.save(missaoExistente);
        return toDto(missaoAtualizada);
    }

    public void deletarMissao(Long id) {
        if (!missaoRepository.existsById(id)) {
            throw new MissaoNaoEncontradaException("Missão não encontrada com id: " + id);
        }
        missaoRepository.deleteById(id);
    }

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

    private Missao toEntity(MissaoDto missaoDTO) {
        Missao missao = new Missao();
        missao.setNome(missaoDTO.getNome());
        missao.setDescricao(missaoDTO.getDescricao());
        missao.setDataInicio(missaoDTO.getDataInicio());
        missao.setDataFim(missaoDTO.getDataFim());
        missao.setPontosBase(missaoDTO.getPontosBase());
        missao.setTipoMaterial(missaoDTO.getTipoMaterial());
        return missao;
    }
}

