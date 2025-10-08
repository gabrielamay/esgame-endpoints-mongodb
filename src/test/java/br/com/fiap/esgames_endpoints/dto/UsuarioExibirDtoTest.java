package br.com.fiap.esgames_endpoints.dto;

import br.com.fiap.esgames_endpoints.model.Usuario;
import br.com.fiap.esgames_endpoints.model.UsuarioRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Testes do UsuarioExibirDto")
class UsuarioExibirDtoTest {

    @Test
    @DisplayName("Deve criar DTO a partir de Usuario")
    void deveCriarDtoAPartirDeUsuario() {
        Usuario usuario = new Usuario();
        usuario.setId("123");
        usuario.setNome("João Silva");
        usuario.setEmail("joao@example.com");
        usuario.setRole(UsuarioRole.USER);
        usuario.setSetor("TI");

        UsuarioExibirDto dto = new UsuarioExibirDto(usuario);

        assertThat(dto.id()).isEqualTo("123");
        assertThat(dto.nome()).isEqualTo("João Silva");
        assertThat(dto.email()).isEqualTo("joao@example.com");
        assertThat(dto.role()).isEqualTo(UsuarioRole.USER);
        assertThat(dto.setor()).isEqualTo("TI");
    }

    @Test
    @DisplayName("Deve criar DTO com todos os parâmetros")
    void deveCriarDtoComTodosParametros() {
        UsuarioExibirDto dto = new UsuarioExibirDto(
                "456",
                "Maria Santos",
                "maria@example.com",
                UsuarioRole.ADMIN,
                "RH"
        );

        assertThat(dto.id()).isEqualTo("456");
        assertThat(dto.nome()).isEqualTo("Maria Santos");
        assertThat(dto.email()).isEqualTo("maria@example.com");
        assertThat(dto.role()).isEqualTo(UsuarioRole.ADMIN);
        assertThat(dto.setor()).isEqualTo("RH");
    }

    @Test
    @DisplayName("Deve manter imutabilidade do record")
    void deveManterImutabilidade() {
        UsuarioExibirDto dto = new UsuarioExibirDto(
                "123",
                "João Silva",
                "joao@example.com",
                UsuarioRole.USER,
                "TI"
        );

        assertThat(dto.id()).isEqualTo("123");
        // Records são imutáveis, não há setters
    }

    @Test
    @DisplayName("Deve verificar equals entre DTOs iguais")
    void deveVerificarEquals() {
        UsuarioExibirDto dto1 = new UsuarioExibirDto(
                "123",
                "João Silva",
                "joao@example.com",
                UsuarioRole.USER,
                "TI"
        );

        UsuarioExibirDto dto2 = new UsuarioExibirDto(
                "123",
                "João Silva",
                "joao@example.com",
                UsuarioRole.USER,
                "TI"
        );

        assertThat(dto1).isEqualTo(dto2);
    }

    @Test
    @DisplayName("Deve ter hashCode consistente")
    void deveTerHashCodeConsistente() {
        UsuarioExibirDto dto1 = new UsuarioExibirDto(
                "123",
                "João Silva",
                "joao@example.com",
                UsuarioRole.USER,
                "TI"
        );

        UsuarioExibirDto dto2 = new UsuarioExibirDto(
                "123",
                "João Silva",
                "joao@example.com",
                UsuarioRole.USER,
                "TI"
        );

        assertThat(dto1.hashCode()).isEqualTo(dto2.hashCode());
    }
}
