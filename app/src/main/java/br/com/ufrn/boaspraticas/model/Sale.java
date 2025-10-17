package br.com.ufrn.boaspraticas.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Sale {
    private final String id;
    private final LocalDateTime createdAt;
    private final List<SaleItem> items = new ArrayList<>();

    public Sale(String id) {
        this.id = id;
        this.createdAt = LocalDateTime.now();
    }

    public void addItem(SaleItem item) {
        items.add(item);
    }

    public List<SaleItem> getItems() {
        return List.copyOf(items);
    }

    public BigDecimal getTotal() {
        return items.stream()
                .map(SaleItem::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
