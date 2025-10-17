package br.com.ufrn.boaspraticas.service;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import br.com.ufrn.boaspraticas.excption.BusinessException;
import br.com.ufrn.boaspraticas.model.Product;
import br.com.ufrn.boaspraticas.model.Sale;
import br.com.ufrn.boaspraticas.model.SaleItem;
import br.com.ufrn.boaspraticas.repository.ProductRepository;

public class SaleService {
    private final ProductRepository productRepo;
    private final List<Sale> sales = new CopyOnWriteArrayList<>();

    public SaleService(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    public Sale registerSale(Map<String, Integer> items) {

        List<Map.Entry<Product, Integer>> resolved = new ArrayList<>();
        for (Map.Entry<String, Integer> e : items.entrySet()) {
            Product p = productRepo.findByCode(e.getKey())
                    .orElseThrow(() -> new BusinessException("Produto não encontrado: " + e.getKey()));
            int qty = e.getValue();
            if (qty <= 0)
                throw new BusinessException("Quantidade inválida para " + p.getCode());
            if (p.getQuantity() < qty)
                throw new BusinessException("Estoque insuficiente para " + p.getCode());
            resolved.add(Map.entry(p, qty));
        }

        for (var entry : resolved) {
            entry.getKey().removeStock(entry.getValue());
            productRepo.save(entry.getKey());
        }

        Sale sale = new Sale(UUID.randomUUID().toString());
        for (var entry : resolved) {
            sale.addItem(new SaleItem(entry.getKey(), entry.getValue()));
        }
        sales.add(sale);
        return sale;
    }

    public List<Sale> listSales() {
        return List.copyOf(sales);
    }

    public Report consolidatedReport() {

        Map<String, long[]> map = new HashMap<>();
        for (Sale s : sales) {
            for (var it : s.getItems()) {
                String code = it.getProduct().getCode();
                long[] arr = map.computeIfAbsent(code, k -> new long[] { 0, 0 });
                arr[0] += it.getQuantity();

            }
        }

        List<ReportLine> lines = sales.stream()
                .flatMap(s -> s.getItems().stream())
                .collect(Collectors.groupingBy(i -> i.getProduct().getCode()))
                .entrySet().stream()
                .map(e -> {
                    String code = e.getKey();
                    int qty = e.getValue().stream().mapToInt(i -> i.getQuantity()).sum();
                    BigDecimal total = e.getValue().stream().map(SaleItem::getTotal)
                            .reduce(BigDecimal.ZERO, BigDecimal::add);
                    return new ReportLine(code, e.getValue().get(0).getProduct().getName(), qty, total);
                })
                .sorted(Comparator.comparingInt(ReportLine::getQuantity).reversed())
                .collect(Collectors.toList());

        BigDecimal totalValue = lines.stream().map(ReportLine::getTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
        int totalItems = lines.stream().mapToInt(ReportLine::getQuantity).sum();

        return new Report(lines, totalItems, totalValue);
    }

    public static class ReportLine {
        private final String code;
        private final String name;
        private final int quantity;
        private final BigDecimal total;

        public ReportLine(String code, String name, int quantity, BigDecimal total) {
            this.code = code;
            this.name = name;
            this.quantity = quantity;
            this.total = total;
        }

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }

        public int getQuantity() {
            return quantity;
        }

        public BigDecimal getTotal() {
            return total;
        }
    }

    public static class Report {
        private final List<ReportLine> lines;
        private final int totalItems;
        private final BigDecimal totalValue;

        public Report(List<ReportLine> lines, int totalItems, BigDecimal totalValue) {
            this.lines = lines;
            this.totalItems = totalItems;
            this.totalValue = totalValue;
        }

        public List<ReportLine> getLines() {
            return lines;
        }

        public int getTotalItems() {
            return totalItems;
        }

        public BigDecimal getTotalValue() {
            return totalValue;
        }
    }
}
