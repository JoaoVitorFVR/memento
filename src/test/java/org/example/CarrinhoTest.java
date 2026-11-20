package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CarrinhoTest {

    Carrinho carrinho;
    HistoricoCarrinho historico;

    @BeforeEach
    void setUp() {
        carrinho = new Carrinho();
        historico = new HistoricoCarrinho();
    }

    @Test
    void deveArmazenarEstados() {
        carrinho.adicionarItem("Pizza", 40.0f);
        historico.salvar(carrinho);
        carrinho.adicionarItem("Suco", 8.0f);
        historico.salvar(carrinho);

        assertEquals(2, historico.getQuantidade());
    }

    @Test
    void deveRestaurarEstadoInicial() {
        carrinho.adicionarItem("Pizza", 40.0f);
        historico.salvar(carrinho);
        carrinho.adicionarItem("Suco", 8.0f);
        historico.salvar(carrinho);
        carrinho.adicionarItem("Sobremesa", 12.0f);

        historico.restaurar(carrinho, 0);

        assertEquals(Arrays.asList("Pizza"), carrinho.getItens());
        assertEquals(40.0f, carrinho.getTotal());
    }

    @Test
    void deveRestaurarEstadoAnterior() {
        carrinho.adicionarItem("Pizza", 40.0f);
        historico.salvar(carrinho);
        carrinho.adicionarItem("Suco", 8.0f);
        historico.salvar(carrinho);
        carrinho.adicionarItem("Sobremesa", 12.0f);
        historico.salvar(carrinho);

        historico.restaurar(carrinho, 1);

        assertEquals(Arrays.asList("Pizza", "Suco"), carrinho.getItens());
        assertEquals(48.0f, carrinho.getTotal());
    }

    @Test
    void deveRetornarExcecaoIndiceInvalido() {
        try {
            historico.restaurar(carrinho, 0);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Índice inválido", e.getMessage());
        }
    }

    @Test
    void deveRetornarExcecaoIndiceNegativo() {
        carrinho.adicionarItem("Pizza", 40.0f);
        historico.salvar(carrinho);
        try {
            historico.restaurar(carrinho, -1);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Índice inválido", e.getMessage());
        }
    }

    @Test
    void deveDesfazerUltimoEstado() {
        carrinho.adicionarItem("Pizza", 40.0f);
        historico.salvar(carrinho);
        carrinho.adicionarItem("Suco", 8.0f);

        historico.desfazer(carrinho);

        assertEquals(Arrays.asList("Pizza"), carrinho.getItens());
        assertEquals(40.0f, carrinho.getTotal());
        assertEquals(0, historico.getQuantidade());
    }

    @Test
    void deveRetornarExcecaoAoDesfazerSemHistorico() {
        try {
            historico.desfazer(carrinho);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Nada para desfazer", e.getMessage());
        }
    }

    @Test
    void deveGuardarUmaCopiaDoEstadoSalvo() {
        carrinho.adicionarItem("Pizza", 40.0f);
        historico.salvar(carrinho);

        carrinho.adicionarItem("Suco", 8.0f);
        historico.restaurar(carrinho, 0);

        assertEquals(Arrays.asList("Pizza"), carrinho.getItens());
    }

    @Test
    void deveManterOEstadoSalvoIntactoDepoisDeRestaurar() {
        carrinho.adicionarItem("Pizza", 40.0f);
        historico.salvar(carrinho);

        historico.restaurar(carrinho, 0);
        carrinho.adicionarItem("Suco", 8.0f);
        historico.restaurar(carrinho, 0);

        assertEquals(Arrays.asList("Pizza"), carrinho.getItens());
        assertEquals(40.0f, carrinho.getTotal());
    }

}
