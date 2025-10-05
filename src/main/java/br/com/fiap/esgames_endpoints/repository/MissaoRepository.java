package br.com.fiap.esgames_endpoints.repository;

import br.com.fiap.esgames_endpoints.model.Missao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MissaoRepository extends MongoRepository<Missao, String> {
    boolean existsByNomeIgnoreCase(String nome);
}
