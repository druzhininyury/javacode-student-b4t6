package ru.javacode.student;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamCollectorsExample {
    public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order("Laptop", 1200.0),
                new Order("Smartphone", 800.0),
                new Order("Laptop", 1500.0),
                new Order("Tablet", 500.0),
                new Order("Smartphone", 900.0),
                new Order("PC", 2100.0)
        );

        // Grouping and total counting.
        Map<String, Double> totalCostByProduct = orders.stream()
                .collect(Collectors.groupingBy(Order::getProduct, Collectors.summingDouble(Order::getCost)));
        System.out.println(totalCostByProduct);

        // Searching for top3.
        List<Map.Entry<String, Double>> top3 = totalCostByProduct.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(3)
                .toList();

        // Printing top3.
        top3.stream().forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));

        // Printing total.
        System.out.println("Total: " + top3.stream().mapToDouble(Map.Entry::getValue).sum());
    }
}