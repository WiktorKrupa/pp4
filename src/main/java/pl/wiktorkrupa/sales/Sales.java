package pl.wiktorkrupa.sales;

import java.util.Optional;

import org.springframework.boot.env.PropertiesPropertySourceLoader;

public class Sales {
    private CartStorage cartStorage;

    public void addToCart(String customerId, String productId) {
        Cart customersCart = loadForCustomer(customerId)
        .orElse(Cart.empty());

        ProductDetails product = getProductDetails(productId)
                .orElseThrow(() -> 

        customersCart,add(product);

        cartStorage.save(customerId, customersCart);

    }

    private Optional<Cart> loadForCustomer(String customerId) {
        return cartStorage.load(customerId);
    }
}
