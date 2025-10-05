package br.com.fiap.esgames_endpoints.repository;

import br.com.fiap.esgames_endpoints.model.Missao;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MissaoRepository extends MongoRepository<Missao, Long> {
    boolean existsByNomeIgnoreCase(String nome);
}
