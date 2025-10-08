package br.com.fiap.esgames_endpoints.service;

import br.com.fiap.esgames_endpoints.dto.UsuarioCadastroDto;
import br.com.fiap.esgames_endpoints.dto.UsuarioExibirDto;
import br.com.fiap.esgames_endpoints.exception.UsuarioNaoEncontradoException;
import br.com.fiap.esgames_endpoints.model.Usuario;
import br.com.fiap.esgames_endpoints.model.UsuarioRole;
import br.com.fiap.esgames_endpoints.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testes do UsuarioService")
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    private Usuario usuario;
    private UsuarioCadastroDto usuarioCadastroDto;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
        usuario.setId("123");
        usuario.setNome("João Silva");
        usuario.setEmail("joao@example.com");
        usuario.setSenha("senhaHash");
        usuario.setSetor("TI");
        usuario.setRole(UsuarioRole.USER);

        usuarioCadastroDto = new UsuarioCadastroDto(
                "João Silva",
                "joao@example.com",
                "senha123",
                "TI",
                UsuarioRole.USER
        );
    }

    @Test
    @DisplayName("Deve cadastrar um novo usuário com sucesso")
    void deveCadastrarUsuarioComSucesso() {
        // Arrange
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        // Act
        UsuarioExibirDto resultado = usuarioService.gravar(usuarioCadastroDto);

        // Assert
        assertThat(resultado).isNotNull();
        assertThat(resultado.nome()).isEqualTo("João Silva");
        assertThat(resultado.email()).isEqualTo("joao@example.com");
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    @Test
    @DisplayName("Deve criptografar a senha ao cadastrar usuário")
    void deveCriptografarSenhaAoCadastrar() {
        // Arrange
        when(usuarioRepository.save(any(Usuario.class))).thenAnswer(invocation -> {
            Usuario usuarioSalvo = invocation.getArgument(0);
            assertThat(usuarioSalvo.getSenha()).isNotEqualTo("senha123");
            assertThat(usuarioSalvo.getSenha()).startsWith("$2a$"); // BCrypt hash
            return usuario;
        });

        // Act
        usuarioService.gravar(usuarioCadastroDto);

        // Assert
        verify(usuarioRepository).save(any(Usuario.class));
    }

    @Test
    @DisplayName("Deve listar todos os usuários com paginação")
    void deveListarTodosUsuariosComPaginacao() {
        // Arrange
        Pageable pageable = PageRequest.of(0, 10);
        Page<Usuario> usuariosPage = new PageImpl<>(Arrays.asList(usuario));
        when(usuarioRepository.findAll(pageable)).thenReturn(usuariosPage);

        // Act
        Page<UsuarioExibirDto> resultado = usuarioService.listarTodosUsuarios(pageable);

        // Assert
        assertThat(resultado).isNotNull();
        assertThat(resultado.getContent()).hasSize(1);
        assertThat(resultado.getContent().get(0).nome()).isEqualTo("João Silva");
        verify(usuarioRepository, times(1)).findAll(pageable);
    }

    @Test
    @DisplayName("Deve buscar usuário por nome com sucesso")
    void deveBuscarUsuarioPorNomeComSucesso() {
        // Arrange
        when(usuarioRepository.findByNome("João Silva")).thenReturn(Optional.of(usuario));

        // Act
        UsuarioExibirDto resultado = usuarioService.buscarPorNome("João Silva");

        // Assert
        assertThat(resultado).isNotNull();
        assertThat(resultado.nome()).isEqualTo("João Silva");
        assertThat(resultado.email()).isEqualTo("joao@example.com");
        verify(usuarioRepository, times(1)).findByNome("João Silva");
    }

    @Test
    @DisplayName("Deve lançar exceção ao buscar usuário por nome inexistente")
    void deveLancarExcecaoAoBuscarPorNomeInexistente() {
        // Arrange
        when(usuarioRepository.findByNome(anyString())).thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() -> usuarioService.buscarPorNome("Inexistente"))
                .isInstanceOf(UsuarioNaoEncontradoException.class)
                .hasMessage("Usuário não encontrado.");
        verify(usuarioRepository, times(1)).findByNome("Inexistente");
    }

    @Test
    @DisplayName("Deve buscar usuário por ID com sucesso")
    void deveBuscarUsuarioPorIdComSucesso() {
        // Arrange
        when(usuarioRepository.findById("123")).thenReturn(Optional.of(usuario));

        // Act
        UsuarioExibirDto resultado = usuarioService.buscarPorId("123");

        // Assert
        assertThat(resultado).isNotNull();
        assertThat(resultado.id()).isEqualTo("123");
        assertThat(resultado.nome()).isEqualTo("João Silva");
        verify(usuarioRepository, times(1)).findById("123");
    }

    @Test
    @DisplayName("Deve lançar exceção ao buscar usuário por ID inexistente")
    void deveLancarExcecaoAoBuscarPorIdInexistente() {
        // Arrange
        when(usuarioRepository.findById(anyString())).thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() -> usuarioService.buscarPorId("999"))
                .isInstanceOf(UsuarioNaoEncontradoException.class)
                .hasMessage("Usuário não encontrado.");
        verify(usuarioRepository, times(1)).findById("999");
    }

    @Test
    @DisplayName("Deve copiar propriedades do DTO para a entidade corretamente")
    void deveCopiarPropriedadesCorretamente() {
        // Arrange
        when(usuarioRepository.save(any(Usuario.class))).thenAnswer(invocation -> {
            Usuario usuarioSalvo = invocation.getArgument(0);
            assertThat(usuarioSalvo.getNome()).isEqualTo("João Silva");
            assertThat(usuarioSalvo.getEmail()).isEqualTo("joao@example.com");
            assertThat(usuarioSalvo.getSetor()).isEqualTo("TI");
            assertThat(usuarioSalvo.getRole()).isEqualTo(UsuarioRole.USER);
            return usuario;
        });

        // Act
        usuarioService.gravar(usuarioCadastroDto);

        // Assert
        verify(usuarioRepository).save(any(Usuario.class));
    }
}
