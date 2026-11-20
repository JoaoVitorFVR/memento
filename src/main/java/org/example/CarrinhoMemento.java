package org.example;

import java.util.ArrayList;
import java.util.List;

public class CarrinhoMemento {

    private final List<String> itens;
    private final float total;

    public CarrinhoMemento(List<String> itens, float total) {
        this.itens = new ArrayList<String>(itens);
        this.total = total;
    }

    public List<String> getItens() {
        return new ArrayList<String>(this.itens);
    }

    public float getTotal() {
        return this.total;
    }
}
