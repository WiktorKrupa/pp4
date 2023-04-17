package pl.wiktorkrupa.productcatalog;

import java.math.BigDecimal;
import java.util.List;

public interface ProductStorage {
    List<Product> allProducts();

    void add(Product newProduct);

    List<Product> allPublishedProducts();

    Product loadById(String productId);

    void changePriceById(String productId, BigDecimal price);
}
