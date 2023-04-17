package pl.wiktorkrupa.productcatalog;

import java.math.BigDecimal;
import java.util.List;

public class DbProductStorage implements ProductStorage{
    @Override
    public List<Product> allProducts() {
        return null;
    }

    @Override
    public void add(Product newProduct) {

    }

    @Override
    public List<Product> allPublishedProducts() {
        return null;
    }

    @Override
    public Product loadById(String productId) {
        return null;
    }

    @Override
    public void changePriceById(String productId, BigDecimal price) {
    }
}
