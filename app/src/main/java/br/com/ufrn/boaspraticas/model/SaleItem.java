package br.com.ufrn.boaspraticas.model;

import java.math.BigDecimal;

public class SaleItem {
    private final Product product;
    private final int quantity;
    private final BigDecimal priceAtSale;

    public SaleItem(Product product, int quantity) {
        if (quantity <= 0)
            throw new IllegalArgumentException("quantity > 0");
        this.product = product;
        this.quantity = quantity;
        this.priceAtSale = product.getPrice();
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getTotal() {
        return priceAtSale.multiply(java.math.BigDecimal.valueOf(quantity));
    }

    public BigDecimal getPriceAtSale() {
        return priceAtSale;
    }
}
