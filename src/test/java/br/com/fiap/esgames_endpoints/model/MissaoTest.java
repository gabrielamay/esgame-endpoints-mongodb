package br.com.fiap.esgames_endpoints.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Testes do Model Missao")
class MissaoTest {

    private Missao missao;

    @BeforeEach
    void setUp() {
        missao = new Missao();
        missao.setId("1");
        missao.setNome("Missão Reciclagem");
        missao.setDescricao("Coletar materiais recicláveis");
        missao.setDataInicio(LocalDate.of(2025, 11, 1));
        missao.setDataFim(LocalDate.of(2025, 11, 30));
        missao.setPontosBase(100);
        missao.setTipoMaterial("Papel");
    }

    @Test
    @DisplayName("Deve criar missão com todos os campos")
    void deveCriarMissaoComTodosCampos() {
        assertThat(missao.getId()).isEqualTo("1");
        assertThat(missao.getNome()).isEqualTo("Missão Reciclagem");
        assertThat(missao.getDescricao()).isEqualTo("Coletar materiais recicláveis");
        assertThat(missao.getDataInicio()).isEqualTo(LocalDate.of(2025, 11, 1));
        assertThat(missao.getDataFim()).isEqualTo(LocalDate.of(2025, 11, 30));
        assertThat(missao.getPontosBase()).isEqualTo(100);
        assertThat(missao.getTipoMaterial()).isEqualTo("Papel");
    }

    @Test
    @DisplayName("Deve permitir atualizar nome da missão")
    void devePermitirAtualizarNome() {
        missao.setNome("Nova Missão");
        assertThat(missao.getNome()).isEqualTo("Nova Missão");
    }

    @Test
    @DisplayName("Deve permitir atualizar descrição")
    void devePermitirAtualizarDescricao() {
        missao.setDescricao("Nova descrição");
        assertThat(missao.getDescricao()).isEqualTo("Nova descrição");
    }

    @Test
    @DisplayName("Deve permitir atualizar datas")
    void devePermitirAtualizarDatas() {
        LocalDate novaDataInicio = LocalDate.of(2025, 12, 1);
        LocalDate novaDataFim = LocalDate.of(2025, 12, 31);

        missao.setDataInicio(novaDataInicio);
        missao.setDataFim(novaDataFim);

        assertThat(missao.getDataInicio()).isEqualTo(novaDataInicio);
        assertThat(missao.getDataFim()).isEqualTo(novaDataFim);
    }

    @Test
    @DisplayName("Deve permitir atualizar pontos base")
    void devePermitirAtualizarPontosBase() {
        missao.setPontosBase(200);
        assertThat(missao.getPontosBase()).isEqualTo(200);
    }

    @Test
    @DisplayName("Deve permitir atualizar tipo de material")
    void devePermitirAtualizarTipoMaterial() {
        missao.setTipoMaterial("Plástico");
        assertThat(missao.getTipoMaterial()).isEqualTo("Plástico");
    }

    @Test
    @DisplayName("Deve verificar equals e hashCode")
    void deveVerificarEqualsEHashCode() {
        Missao missao1 = new Missao();
        missao1.setId("1");
        missao1.setNome("Missão Reciclagem");

        Missao missao2 = new Missao();
        missao2.setId("1");
        missao2.setNome("Missão Reciclagem");

        assertThat(missao1).isEqualTo(missao2);
        assertThat(missao1.hashCode()).isEqualTo(missao2.hashCode());
    }

    @Test
    @DisplayName("Deve criar missão usando construtor com todos os argumentos")
    void deveCriarMissaoComConstrutorCompleto() {
        Missao novaMissao = new Missao(
                "2",
                "Missão Plástico",
                "Coletar plásticos",
                LocalDate.of(2025, 12, 1),
                LocalDate.of(2025, 12, 31),
                150,
                "Plástico"
        );

        assertThat(novaMissao.getId()).isEqualTo("2");
        assertThat(novaMissao.getNome()).isEqualTo("Missão Plástico");
        assertThat(novaMissao.getDescricao()).isEqualTo("Coletar plásticos");
        assertThat(novaMissao.getPontosBase()).isEqualTo(150);
        assertThat(novaMissao.getTipoMaterial()).isEqualTo("Plástico");
    }

    @Test
    @DisplayName("Deve criar missão usando construtor sem argumentos")
    void deveCriarMissaoComConstrutorVazio() {
        Missao novaMissao = new Missao();
        assertThat(novaMissao).isNotNull();
        assertThat(novaMissao.getId()).isNull();
    }
}
