package br.com.fiap.esgames_endpoints.repository;

import br.com.fiap.esgames_endpoints.model.RegistroAtividade;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RankingRepository extends MongoRepository<RegistroAtividade, String> {

    @Aggregation(pipeline = {
            "{ $group: { _id: '$setor', pontosTotais: { $sum: '$pontosGerados' } } }",
            "{ $sort: { pontosTotais: -1 } }"
    })
    List<Map<String, Object>> buscarRankingPorSetor();

    @Aggregation(pipeline = {
            "{ $group: { _id: { nomeUsuario: '$nomeUsuario', setor: '$setor' }, pontos: { $sum: '$pontosGerados' } } }",
            "{ $sort: { pontos: -1 } }"
    })
    List<Map<String, Object>> buscarRankingIndividual();
}