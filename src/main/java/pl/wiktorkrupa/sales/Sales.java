package pl.wiktorkrupa.sales;

import java.util.Optional;

import org.springframework.boot.env.PropertiesPropertySourceLoader;

import static org.springframework.cglib.core.TypeUtils.add;

public class Sales {
    private CartStorage cartStorage;
    private ProductDetailsProvider productDetailsProvider;

    public void addToCart(String customerId, String productId) {
        Cart customerCart = loadForCustomer(customerId)
            .orElse(Cart.empty());

        ProductDetails product = loadProductDetails(productId)
                .orElseThrow(() -> new NoSuchProductException());

        customerCart.add(product);



    }


    private Optional<ProductDetails> loadProductDetails(String productId){
        return productDetailsProvider.load(productId);
    }

    private Optional<Cart> loadForCustomer(String customerId) {
        return cartStorage.load(customerId);
    }
}
