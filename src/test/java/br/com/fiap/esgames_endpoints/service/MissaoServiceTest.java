package br.com.fiap.esgames_endpoints.service;

import br.com.fiap.esgames_endpoints.dto.MissaoDto;
import br.com.fiap.esgames_endpoints.exception.MissaoJaExistenteException;
import br.com.fiap.esgames_endpoints.exception.MissaoNaoEncontradaException;
import br.com.fiap.esgames_endpoints.model.Missao;
import br.com.fiap.esgames_endpoints.repository.MissaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testes do MissaoService")
class MissaoServiceTest {

    @Mock
    private MissaoRepository missaoRepository;

    @InjectMocks
    private MissaoService missaoService;

    private Missao missao;
    private MissaoDto missaoDto;

    @BeforeEach
    void setUp() {
        missao = new Missao();
        missao.setNome("Missão Reciclagem");
        missao.setDescricao("Coletar materiais recicláveis");
        missao.setDataInicio(LocalDate.now().plusDays(1));
        missao.setDataFim(LocalDate.now().plusDays(30));
        missao.setPontosBase(100);
        missao.setTipoMaterial("Papel");

        missaoDto = new MissaoDto(
                "Missão Reciclagem",
                "Coletar materiais recicláveis",
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(30),
                100,
                "Papel"
        );
    }

    @Test
    @DisplayName("Deve listar todas as missões")
    void deveListarTodasMissoes() {
        // Arrange
        List<Missao> missoes = Arrays.asList(missao);
        when(missaoRepository.findAll()).thenReturn(missoes);

        // Act
        List<MissaoDto> resultado = missaoService.listarMissoes();

        // Assert
        assertThat(resultado).isNotNull();
        assertThat(resultado).hasSize(1);
        assertThat(resultado.get(0).getNome()).isEqualTo("Missão Reciclagem");
        verify(missaoRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Deve criar uma nova missão com sucesso")
    void deveCriarMissaoComSucesso() {
        // Arrange
        when(missaoRepository.existsByNomeIgnoreCase(anyString())).thenReturn(false);
        when(missaoRepository.save(any(Missao.class))).thenReturn(missao);

        // Act
        MissaoDto resultado = missaoService.criarMissao(missaoDto);

        // Assert
        assertThat(resultado).isNotNull();
        assertThat(resultado.getNome()).isEqualTo("Missão Reciclagem");
        assertThat(resultado.getPontosBase()).isEqualTo(100);
        verify(missaoRepository, times(1)).existsByNomeIgnoreCase("Missão Reciclagem");
        verify(missaoRepository, times(1)).save(any(Missao.class));
    }

    @Test
    @DisplayName("Deve lançar exceção ao criar missão com nome duplicado")
    void deveLancarExcecaoAoCriarMissaoDuplicada() {
        // Arrange
        when(missaoRepository.existsByNomeIgnoreCase(anyString())).thenReturn(true);

        // Act & Assert
        assertThatThrownBy(() -> missaoService.criarMissao(missaoDto))
                .isInstanceOf(MissaoJaExistenteException.class)
                .hasMessageContaining("Já existe uma missão cadastrada com o nome");
        verify(missaoRepository, times(1)).existsByNomeIgnoreCase("Missão Reciclagem");
        verify(missaoRepository, never()).save(any(Missao.class));
    }

    @Test
    @DisplayName("Deve atualizar uma missão existente com sucesso")
    void deveAtualizarMissaoComSucesso() {
        // Arrange
        when(missaoRepository.findById("1")).thenReturn(Optional.of(missao));
        when(missaoRepository.save(any(Missao.class))).thenReturn(missao);

        MissaoDto missaoAtualizada = new MissaoDto(
                "Missão Atualizada",
                "Descrição atualizada",
                LocalDate.now().plusDays(2),
                LocalDate.now().plusDays(35),
                150,
                "Plástico"
        );

        // Act
        MissaoDto resultado = missaoService.atualizarMissao("1", missaoAtualizada);

        // Assert
        assertThat(resultado).isNotNull();
        verify(missaoRepository, times(1)).findById("1");
        verify(missaoRepository, times(1)).save(any(Missao.class));
    }

    @Test
    @DisplayName("Deve lançar exceção ao atualizar missão inexistente")
    void deveLancarExcecaoAoAtualizarMissaoInexistente() {
        // Arrange
        when(missaoRepository.findById(anyString())).thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() -> missaoService.atualizarMissao("999", missaoDto))
                .isInstanceOf(MissaoNaoEncontradaException.class)
                .hasMessageContaining("Missão não encontrada com id");
        verify(missaoRepository, times(1)).findById("999");
        verify(missaoRepository, never()).save(any(Missao.class));
    }

    @Test
    @DisplayName("Deve retornar lista vazia quando não houver missões")
    void deveRetornarListaVaziaQuandoNaoHouverMissoes() {
        // Arrange
        when(missaoRepository.findAll()).thenReturn(Arrays.asList());

        // Act
        List<MissaoDto> resultado = missaoService.listarMissoes();

        // Assert
        assertThat(resultado).isEmpty();
        verify(missaoRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Deve verificar se o nome é case-insensitive ao criar missão")
    void deveVerificarNomeCaseInsensitiveAoCriar() {
        // Arrange
        when(missaoRepository.existsByNomeIgnoreCase("missão reciclagem")).thenReturn(true);

        MissaoDto missaoDtoLowerCase = new MissaoDto(
                "missão reciclagem",
                "Coletar materiais recicláveis",
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(30),
                100,
                "Papel"
        );

        // Act & Assert
        assertThatThrownBy(() -> missaoService.criarMissao(missaoDtoLowerCase))
                .isInstanceOf(MissaoJaExistenteException.class);
        verify(missaoRepository, times(1)).existsByNomeIgnoreCase("missão reciclagem");
    }
}
