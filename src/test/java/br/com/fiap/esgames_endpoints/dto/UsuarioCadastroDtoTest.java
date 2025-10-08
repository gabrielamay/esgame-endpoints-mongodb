package br.com.fiap.esgames_endpoints.dto;

import br.com.fiap.esgames_endpoints.model.UsuarioRole;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Testes do UsuarioCadastroDto")
class UsuarioCadastroDtoTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("Deve criar DTO válido com todos os campos")
    void deveCriarDtoValido() {
        UsuarioCadastroDto dto = new UsuarioCadastroDto(
                "João Silva",
                "joao@example.com",
                "senha123",
                "TI",
                UsuarioRole.USER
        );

        Set<ConstraintViolation<UsuarioCadastroDto>> violations = validator.validate(dto);
        assertThat(violations).isEmpty();
    }

    @Test
    @DisplayName("Deve validar nome obrigatório")
    void deveValidarNomeObrigatorio() {
        UsuarioCadastroDto dto = new UsuarioCadastroDto(
                "",
                "joao@example.com",
                "senha123",
                "TI",
                UsuarioRole.USER
        );

        Set<ConstraintViolation<UsuarioCadastroDto>> violations = validator.validate(dto);
        assertThat(violations).isNotEmpty();
        assertThat(violations).anyMatch(v -> v.getMessage().contains("nome é obrigatório"));
    }

    @Test
    @DisplayName("Deve validar email obrigatório")
    void deveValidarEmailObrigatorio() {
        UsuarioCadastroDto dto = new UsuarioCadastroDto(
                "João Silva",
                "",
                "senha123",
                "TI",
                UsuarioRole.USER
        );

        Set<ConstraintViolation<UsuarioCadastroDto>> violations = validator.validate(dto);
        assertThat(violations).isNotEmpty();
        assertThat(violations).anyMatch(v -> v.getMessage().contains("email é obrigatório"));
    }

    @Test
    @DisplayName("Deve validar formato de email")
    void deveValidarFormatoEmail() {
        UsuarioCadastroDto dto = new UsuarioCadastroDto(
                "João Silva",
                "email-invalido",
                "senha123",
                "TI",
                UsuarioRole.USER
        );

        Set<ConstraintViolation<UsuarioCadastroDto>> violations = validator.validate(dto);
        assertThat(violations).isNotEmpty();
    }

    @Test
    @DisplayName("Deve validar senha obrigatória")
    void deveValidarSenhaObrigatoria() {
        UsuarioCadastroDto dto = new UsuarioCadastroDto(
                "João Silva",
                "joao@example.com",
                "",
                "TI",
                UsuarioRole.USER
        );

        Set<ConstraintViolation<UsuarioCadastroDto>> violations = validator.validate(dto);
        assertThat(violations).isNotEmpty();
        assertThat(violations).anyMatch(v -> v.getMessage().contains("senha é obrigatório"));
    }

    @Test
    @DisplayName("Deve validar tamanho mínimo da senha")
    void deveValidarTamanhoMinimoSenha() {
        UsuarioCadastroDto dto = new UsuarioCadastroDto(
                "João Silva",
                "joao@example.com",
                "12345",
                "TI",
                UsuarioRole.USER
        );

        Set<ConstraintViolation<UsuarioCadastroDto>> violations = validator.validate(dto);
        assertThat(violations).isNotEmpty();
        assertThat(violations).anyMatch(v -> v.getMessage().contains("entre 6 e 20 caracteres"));
    }

    @Test
    @DisplayName("Deve validar tamanho máximo da senha")
    void deveValidarTamanhoMaximoSenha() {
        UsuarioCadastroDto dto = new UsuarioCadastroDto(
                "João Silva",
                "joao@example.com",
                "senhaComMaisDe20Caracteres123456",
                "TI",
                UsuarioRole.USER
        );

        Set<ConstraintViolation<UsuarioCadastroDto>> violations = validator.validate(dto);
        assertThat(violations).isNotEmpty();
        assertThat(violations).anyMatch(v -> v.getMessage().contains("entre 6 e 20 caracteres"));
    }

    @Test
    @DisplayName("Deve aceitar senha com 6 caracteres")
    void deveAceitarSenhaCom6Caracteres() {
        UsuarioCadastroDto dto = new UsuarioCadastroDto(
                "João Silva",
                "joao@example.com",
                "123456",
                "TI",
                UsuarioRole.USER
        );

        Set<ConstraintViolation<UsuarioCadastroDto>> violations = validator.validate(dto);
        assertThat(violations).isEmpty();
    }

    @Test
    @DisplayName("Deve aceitar senha com 20 caracteres")
    void deveAceitarSenhaCom20Caracteres() {
        UsuarioCadastroDto dto = new UsuarioCadastroDto(
                "João Silva",
                "joao@example.com",
                "12345678901234567890",
                "TI",
                UsuarioRole.USER
        );

        Set<ConstraintViolation<UsuarioCadastroDto>> violations = validator.validate(dto);
        assertThat(violations).isEmpty();
    }

    @Test
    @DisplayName("Deve permitir setor e role opcionais")
    void devePermitirSetorERoleOpcionais() {
        UsuarioCadastroDto dto = new UsuarioCadastroDto(
                "João Silva",
                "joao@example.com",
                "senha123",
                null,
                null
        );

        Set<ConstraintViolation<UsuarioCadastroDto>> violations = validator.validate(dto);
        assertThat(violations).isEmpty();
    }
}
