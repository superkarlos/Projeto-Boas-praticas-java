package br.com.ufrn.boaspraticas.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {
    private final String code;
    private String name;
    private BigDecimal price;
    private int quantity;

    public Product(String code, String name, BigDecimal price, int quantity) {
        if (code == null || code.isBlank())
            throw new IllegalArgumentException("code required");
        this.code = code;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void addStock(int amount) {
        if (amount <= 0)
            throw new IllegalArgumentException("amount > 0");
        this.quantity += amount;
    }

    public void removeStock(int amount) {
        if (amount <= 0)
            throw new IllegalArgumentException("amount > 0");
        if (amount > this.quantity)
            throw new IllegalArgumentException("Insufficient stock");
        this.quantity -= amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Product))
            return false;
        Product p = (Product) o;
        return code.equals(p.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
