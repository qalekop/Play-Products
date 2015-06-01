package models;

import play.data.validation.Constraints;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import static models.ProductTypeEnum.*;

/**
 * Created by akopylov on 13.05.2015.
 */
public class Product {
    @Constraints.Required
    private int ean;

    @Constraints.Required
    private String name;

    private String description;

    private Set<ProductTypeEnum> types;

    private static List<Product> products = new ArrayList<>(5);
    static {
        products.add(new Product("Paperclips 1", 1, "Paperclips description 1", STONE));
        products.add(new Product("Paperclips 2", 2, "Paperclips description 2"));
        products.add(new Product("Paperclips 3", 3, "Paperclips description 3", SCISSORS, LIZARD));
        products.add(new Product("Paperclips 4", 4, "Paperclips description 4", STONE, PAPER, SPOCK));
        products.add(new Product("Paperclips 5", 5, "Paperclips description 5", LIZARD, SPOCK));
    }

    public Product(String name, int ean, String description, ProductTypeEnum... types) {
        this.description = description;
        this.ean = ean;
        this.name = name;
        this.types = new HashSet<>(Arrays.asList(types));
    }

    public Product() {
        this.description = "";
        this.name = "";
        this.types = new HashSet<>();
    }

    @Override
    public String toString() {
        return "Product{" +
                "ean=" + ean +
                ", name='" + name + '\'' +
                '}';
    }

    public static List<Product> findAll() {
        return products;
    }

    public static Product find(final String name) {
        return find(PredicateFactory.predicate(name));
    }

    public static Product find(final int ean) {
        return find(PredicateFactory.predicate(ean));
    }

    private static Product find(final Predicate<Product> predicate) {
        return products.stream().filter(predicate).findAny().orElse(null);
    }

    public static boolean remove(Product product) {
//        return products.remove(product);
        throw new RuntimeException("Fake exception with product ean=" + product.getEan());
    }

    public void save() {
        products.remove(find(this.ean));
        products.add(this);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEan() {
        return ean;
    }

    public Set<ProductTypeEnum> getTypes() {
        return types;
    }

    public void setTypes(Set<ProductTypeEnum> types) {
        this.types = types;
    }

    public void setEan(int ean) {
        this.ean = ean;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
