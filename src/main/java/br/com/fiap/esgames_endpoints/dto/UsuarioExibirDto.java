package br.com.fiap.esgames_endpoints.dto;

import br.com.fiap.esgames_endpoints.model.Usuario;
import br.com.fiap.esgames_endpoints.model.UsuarioRole;

public record UsuarioExibirDto(
        String id,
        String nome,
        String email,
        UsuarioRole role,
        String setor
) {
    public UsuarioExibirDto(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getRole(),
                usuario.getSetor()
        );
    }
}