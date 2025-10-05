package br.com.fiap.esgames_endpoints.repository;

import br.com.fiap.esgames_endpoints.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {

    UserDetails findByEmail(String email);

    Optional<Usuario> findByNome(String nome);
}