package br.com.fiap.esgames_endpoints.repository;

import br.com.fiap.esgames_endpoints.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {

    Optional<Usuario> findByNome(String nome);
}