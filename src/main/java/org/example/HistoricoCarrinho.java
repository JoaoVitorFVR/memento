package org.example;

import java.util.ArrayList;
import java.util.List;

public class HistoricoCarrinho {

    private List<CarrinhoMemento> mementos = new ArrayList<CarrinhoMemento>();

    public void salvar(Carrinho carrinho) {
        this.mementos.add(carrinho.criarMemento());
    }

    public void restaurar(Carrinho carrinho, int indice) {
        if (indice < 0 || indice > this.mementos.size() - 1) {
            throw new IllegalArgumentException("Índice inválido");
        }
        carrinho.restaurar(this.mementos.get(indice));
    }

    public void desfazer(Carrinho carrinho) {
        if (this.mementos.isEmpty()) {
            throw new IllegalArgumentException("Nada para desfazer");
        }
        CarrinhoMemento ultimo = this.mementos.remove(this.mementos.size() - 1);
        carrinho.restaurar(ultimo);
    }

    public int getQuantidade() {
        return this.mementos.size();
    }
}
