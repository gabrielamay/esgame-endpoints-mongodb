package br.com.fiap.esgames_endpoints.service;

import br.com.fiap.esgames_endpoints.dto.SeloDto;
import br.com.fiap.esgames_endpoints.exception.SeloJaExistenteException;
import br.com.fiap.esgames_endpoints.model.Selo;
import br.com.fiap.esgames_endpoints.repository.SeloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeloService {

    private final SeloRepository seloRepository;

    @Autowired
    public SeloService(SeloRepository seloRepository) {
        this.seloRepository = seloRepository;
    }

    public List<Selo> listarSelos() {
        return seloRepository.findAll();
    }

    public Selo buscarPorId(Long id) {
        return seloRepository.findById(id).orElseThrow(() -> new RuntimeException("Selo não encontrado"));
    }

    public Selo criarSelo(SeloDto selo) {
        // Verificar se já existe selo com mesmo nome
        boolean existeSelo = seloRepository.existsByNomeIgnoreCase(selo.nome());
        if (existeSelo) {
            throw new SeloJaExistenteException("Já existe um selo cadastrado com o nome: " + selo.nome());
        }
        return seloRepository.save(selo.toDomain());
    }

    public Selo atualizarSelo(Long id, Selo seloAtualizado) {
        Selo selo = buscarPorId(id);
        selo.setNome(seloAtualizado.getNome());
        selo.setCor(seloAtualizado.getCor());
        selo.setPontuacaoMinima(seloAtualizado.getPontuacaoMinima());
        return seloRepository.save(selo);
    }

    public void deletarSelo(Long id) {
        seloRepository.deleteById(id);
    }
}
