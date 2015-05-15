package models;

import java.util.function.Predicate;

/**
 * Created by akopylov on 13.05.2015.
 */
public class PredicateFactory {
    public static Predicate<Product> predicate(final String name) {
        return result -> result.getName().equalsIgnoreCase(name);
    }

    public static Predicate<Product> predicate(final int ean) {
        return result -> result.getEan() == ean;
    }
}
