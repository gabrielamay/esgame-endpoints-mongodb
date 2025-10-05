package br.com.fiap.esgames_endpoints.dto;

import br.com.fiap.esgames_endpoints.model.UsuarioRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record UsuarioCadastroDto(
        @NotBlank(message = "O campo nome é obrigatório")
        String nome,

        @Email
        @NotBlank(message = "O campo email é obrigatório")
        String email,

        @Size(min = 6, max = 20, message = "A senha deve conter entre 6 e 20 caracteres.")
        @NotBlank(message = "O campo senha é obrigatório")
        String senha,

        String setor,

        UsuarioRole role
) {
}
