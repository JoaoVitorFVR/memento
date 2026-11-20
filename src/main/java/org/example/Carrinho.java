package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Carrinho {

    private List<String> itens = new ArrayList<String>();
    private float total;

    public void adicionarItem(String nome, float preco) {
        this.itens.add(nome);
        this.total += preco;
    }

    public List<String> getItens() {
        return Collections.unmodifiableList(this.itens);
    }

    public float getTotal() {
        return this.total;
    }

    public CarrinhoMemento criarMemento() {
        return new CarrinhoMemento(this.itens, this.total);
    }

    public void restaurar(CarrinhoMemento memento) {
        this.itens = memento.getItens();
        this.total = memento.getTotal();
    }
}
