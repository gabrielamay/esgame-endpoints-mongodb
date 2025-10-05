package br.com.fiap.esgames_endpoints.repository;

import br.com.fiap.esgames_endpoints.model.RegistroAtividade;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RankingRepository extends MongoRepository<RegistroAtividade, String> {

    // Ranking por setor (faz um "join" com a coleção de usuários)
    @Aggregation(pipeline = {
            "{ $lookup: { from: 'usuarios', localField: 'usuarioId', foreignField: '_id', as: 'usuario' } }",
            "{ $unwind: '$usuario' }",
            "{ $group: { _id: '$usuario.setor', pontosTotais: { $sum: '$pontosGerados' } } }",
            "{ $project: { _id: 0, setor: '$_id', pontosTotais: 1 } }",
            "{ $sort: { pontosTotais: -1 } }"
    })
    List<Map<String, Object>> buscarRankingPorSetor();

    // Ranking individual (join com usuário e agrupamento por nome)
    @Aggregation(pipeline = {
            "{ $lookup: { from: 'usuarios', localField: 'usuarioId', foreignField: '_id', as: 'usuario' } }",
            "{ $unwind: '$usuario' }",
            "{ $group: { _id: '$usuario.nome', pontos: { $sum: '$pontosGerados' }, setor: { $first: '$usuario.setor' } } }",
            "{ $project: { _id: 0, nomeUsuario: '$_id', pontos: 1, setor: 1 } }",
            "{ $sort: { pontos: -1 } }"
    })
    List<Map<String, Object>> buscarRankingIndividual();
}