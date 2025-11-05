import java.util.*;
import java.util.stream.*;
import java.util.function.*;
import java.util.Map.Entry;

class Product {
    String name;
    double price;
    String category;
    Product(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }
}

public class ProductStreamOps {
    public static void main(String[] args) {
        List<Product> list = Arrays.asList(
            new Product("Laptop", 80000, "Electronics"),
            new Product("Phone", 60000, "Electronics"),
            new Product("Table", 10000, "Furniture"),
            new Product("Chair", 5000, "Furniture"),
            new Product("Shirt", 2000, "Clothing"),
            new Product("Jeans", 3000, "Clothing")
        );

        Map<String, List<Product>> group = list.stream()
            .collect(Collectors.groupingBy(p -> p.category));
        group.forEach((k, v) -> {
            System.out.println(k + ":");
            v.forEach(p -> System.out.println("  " + p.name));
        });

        Map<String, Optional<Product>> maxPrice = list.stream()
            .collect(Collectors.groupingBy(p -> p.category,
                Collectors.maxBy(Comparator.comparingDouble(p -> p.price))));
        System.out.println("\nMost Expensive Product in Each Category:");
        maxPrice.forEach((k, v) -> System.out.println(k + " -> " + v.get().name));

        double avg = list.stream()
            .collect(Collectors.averagingDouble(p -> p.price));
        System.out.println("\nAverage Price of All Products: " + avg);
    }
}
