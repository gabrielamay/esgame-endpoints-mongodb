//package br.com.fiap.esgames_endpoints.model;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.security.core.GrantedAuthority;
//
//import java.util.Collection;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@DisplayName("Testes do Model Usuario")
//class UsuarioTest {
//
//    private Usuario usuario;
//
//    @BeforeEach
//    void setUp() {
//        usuario = new Usuario();
//        usuario.setId("123");
//        usuario.setNome("João Silva");
//        usuario.setEmail("joao@example.com");
//        usuario.setSenha("senha123");
//        usuario.setSetor("TI");
//        usuario.setRole(UsuarioRole.USER);
//    }
//
//    @Test
//    @DisplayName("Deve criar usuário com todos os campos")
//    void deveCriarUsuarioComTodosCampos() {
//        assertThat(usuario.getId()).isEqualTo("123");
//        assertThat(usuario.getNome()).isEqualTo("João Silva");
//        assertThat(usuario.getEmail()).isEqualTo("joao@example.com");
//        assertThat(usuario.getSenha()).isEqualTo("senha123");
//        assertThat(usuario.getSetor()).isEqualTo("TI");
//        assertThat(usuario.getRole()).isEqualTo(UsuarioRole.USER);
//    }
//
//    @Test
//    @DisplayName("Deve retornar username como email")
//    void deveRetornarUsernameComoEmail() {
//        assertThat(usuario.getUsername()).isEqualTo("joao@example.com");
//    }
//
//    @Test
//    @DisplayName("Deve retornar password como senha")
//    void deveRetornarPasswordComoSenha() {
//        assertThat(usuario.getPassword()).isEqualTo("senha123");
//    }
//
//    @Test
//    @DisplayName("Deve ter conta não expirada")
//    void deveTeContaNaoExpirada() {
//        assertThat(usuario.isAccountNonExpired()).isTrue();
//    }
//
//    @Test
//    @DisplayName("Deve ter conta não bloqueada")
//    void deveTeContaNaoBloqueada() {
//        assertThat(usuario.isAccountNonLocked()).isTrue();
//    }
//
//    @Test
//    @DisplayName("Deve ter credenciais não expiradas")
//    void deveTeCredenciaisNaoExpiradas() {
//        assertThat(usuario.isCredentialsNonExpired()).isTrue();
//    }
//
//    @Test
//    @DisplayName("Deve estar habilitado")
//    void deveEstarHabilitado() {
//        assertThat(usuario.isEnabled()).isTrue();
//    }
//
//    @Test
//    @DisplayName("Deve retornar ROLE_USER para usuário comum")
//    void deveRetornarRoleUserParaUsuarioComum() {
//        usuario.setRole(UsuarioRole.USER);
//        Collection<? extends GrantedAuthority> authorities = usuario.getAuthorities();
//
//        assertThat(authorities).hasSize(1);
//        assertThat(authorities).anyMatch(auth -> auth.getAuthority().equals("ROLE_USER"));
//    }
//
//    @Test
//    @DisplayName("Deve retornar ROLE_ADMIN e ROLE_USER para admin")
//    void deveRetornarRolesParaAdmin() {
//        usuario.setRole(UsuarioRole.ADMIN);
//        Collection<? extends GrantedAuthority> authorities = usuario.getAuthorities();
//
//        assertThat(authorities).hasSize(2);
//        assertThat(authorities).anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));
//        assertThat(authorities).anyMatch(auth -> auth.getAuthority().equals("ROLE_USER"));
//    }
//
//    @Test
//    @DisplayName("Deve verificar equals e hashCode")
//    void deveVerificarEqualsEHashCode() {
//        Usuario usuario1 = new Usuario();
//        usuario1.setId("123");
//        usuario1.setNome("João Silva");
//        usuario1.setEmail("joao@example.com");
//
//        Usuario usuario2 = new Usuario();
//        usuario2.setId("123");
//        usuario2.setNome("João Silva");
//        usuario2.setEmail("joao@example.com");
//
//        assertThat(usuario1).isEqualTo(usuario2);
//        assertThat(usuario1.hashCode()).isEqualTo(usuario2.hashCode());
//    }
//
//    @Test
//    @DisplayName("Deve permitir atualizar senha")
//    void devePermitirAtualizarSenha() {
//        usuario.setSenha("novaSenha123");
//        assertThat(usuario.getSenha()).isEqualTo("novaSenha123");
//    }
//
//    @Test
//    @DisplayName("Deve criar usuário usando construtor com todos os argumentos")
//    void deveCriarUsuarioComConstrutorCompleto() {
//        Usuario novoUsuario = new Usuario(
//                "456",
//                "Maria Santos",
//                "maria@example.com",
//                "senha456",
//                "RH",
//                UsuarioRole.ADMIN
//        );
//
//        assertThat(novoUsuario.getId()).isEqualTo("456");
//        assertThat(novoUsuario.getNome()).isEqualTo("Maria Santos");
//        assertThat(novoUsuario.getEmail()).isEqualTo("maria@example.com");
//        assertThat(novoUsuario.getSetor()).isEqualTo("RH");
//        assertThat(novoUsuario.getRole()).isEqualTo(UsuarioRole.ADMIN);
//    }
//}
