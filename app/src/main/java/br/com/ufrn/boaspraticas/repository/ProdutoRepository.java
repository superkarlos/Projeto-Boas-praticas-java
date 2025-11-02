package br.com.ufrn.boaspraticas.repository;

import java.util.ArrayList;
import java.util.List;
import br.com.ufrn.boaspraticas.model.Produto;

public class ProdutoRepository {
    private List<Produto> produtos = new ArrayList<>();

    public void salvar(Produto produto) {
        produtos.add(produto);
    }

    public Produto buscarPorCodigo(int codigo) {
        return produtos.stream()
                .filter(p -> p.getCodigo() == codigo)
                .findFirst()
                .orElse(null);
    }

    public List<Produto> listarTodos() {
        return produtos;
    }
}
