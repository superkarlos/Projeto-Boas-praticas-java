package br.com.ufrn.boaspraticas.service;

import java.math.BigDecimal;
import java.util.List;

import br.com.ufrn.boaspraticas.excption.BusinessException;
import br.com.ufrn.boaspraticas.model.Product;
import br.com.ufrn.boaspraticas.repository.ProductRepository;

public class ProductService {
    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public void registerProduct(String code, String name, BigDecimal price) {
        if (repo.existsByCode(code))
            throw new BusinessException("Código já existe");
        Product p = new Product(code, name, price, 0);
        repo.save(p);
    }

    public List<Product> listProducts() {
        return repo.findAll();
    }

    public void addStock(String code, int amount) {
        Product p = repo.findByCode(code).orElseThrow(() -> new BusinessException("Produto não encontrado"));
        p.addStock(amount);
        repo.save(p);
    }

    public Product findByCode(String code) {
        return repo.findByCode(code).orElseThrow(() -> new BusinessException("Produto não encontrado"));
    }
}
