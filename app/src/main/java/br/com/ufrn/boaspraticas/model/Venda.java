package br.com.ufrn.boaspraticas.model;

import java.util.ArrayList;
import java.util.List;

public class Venda {
    
    private int id;
    private TipoVenda tipo;
    private String enderecoEntrega;
    private List<ItemVenda> itens = new ArrayList<>();

    public Venda(int id, TipoVenda tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    public void adicionarItem(ItemVenda item) {
        itens.add(item);
    }

    public double getTotal() {
        return itens.stream().mapToDouble(ItemVenda::getSubtotal).sum();
    }

    public List<ItemVenda> getItens() {
        return itens;
    }

    public TipoVenda getTipo() {
        return tipo;
    }

    public String getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(String enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }

    @Override
    public String toString() {
        return String.format("Venda %d (%s) - Total: R$ %.2f", id, tipo, getTotal());
    }
}