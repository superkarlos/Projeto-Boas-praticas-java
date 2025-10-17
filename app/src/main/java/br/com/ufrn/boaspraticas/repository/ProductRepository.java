package br.com.ufrn.boaspraticas.repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import br.com.ufrn.boaspraticas.model.Product;

public class ProductRepository {
    private final Map<String, Product> store = new ConcurrentHashMap<>();

    public Optional<Product> findByCode(String code) {
        return Optional.ofNullable(store.get(code));
    }

    public List<Product> findAll() {
        return new ArrayList<>(store.values());
    }

    public void save(Product product) {
        store.put(product.getCode(), product);
    }

    public boolean existsByCode(String code) {
        return store.containsKey(code);
    }
}
