package pl.wiktorkrupa.productcatalog;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;

//schema.org/Product

public class ProductCatalogTest {

    @Test
    void itAllowsToListMyProducts(){
        ProductCatalog catalog = thereIsProductCatalog();


        List<Product> products = catalog.allProducts();



        assertListIsEmpty(products);
    }

    @Test
    void itAllowsToAddProduct(){
        ProductCatalog catalog = thereIsProductCatalog();

        String productId = catalog.addProduct("lego set 1234", "sports car");

        List<Product> products = catalog.allProducts();
        assert 1 == products.size();




    }
    @Test
    void itAllowsToLoadProductDetails(){
        ProductCatalog catalog = thereIsProductCatalog();

        String productId = catalog.addProduct("lego set 1234", "sports car");

        Product loadedProduct = catalog.loadById(productId);
        assert loadedProduct.getID().equals((productId));
        assert loadedProduct.getName().equals("lego set 1234");

    }
    @Test
    void itAllowsToChangePrice(){
        ProductCatalog catalog = thereIsProductCatalog();

        String productId = catalog.addProduct("lego set 1234", "sports car");

        catalog.changePrice(productId, BigDecimal.valueOf(20.20));

        Product loadedProduct = catalog.loadById(productId);
        assertEquals(BigDecimal.valueOf(20.20), loadedProduct.getPrice());
    }
    @Test
    void itAllowsToAssignImage() {
        ProductCatalog catalog = thereIsProductCatalog();
        String productId = catalog.addProduct("lego set 8083", "nice one");

        catalog.assignImage(productId, "foo/boo/nice_image.jpeg");

        Product loadedProduct = catalog.loadById(productId);
        assertEquals("foo/boo/nice_image.jpeg", loadedProduct.getImage());
    }

    @Test
    void itAllowsToPublishProduct() {
        ProductCatalog catalog = thereIsProductCatalog();
        String productId = catalog.addProduct("lego set 8083", "nice one");
        catalog.changePrice(productId, BigDecimal.valueOf(10));
        catalog.assignImage(productId, "nice.jpeg");

        catalog.publishProduct(productId);

        List<Product> publishedProducts = catalog.allPublishedProducts();
        assertDoesNotThrow(() -> catalog.publishProduct(productId));
        assertEquals(1, publishedProducts.size());
    }

    @Test
    void draftProductsAreNotListedForBeingSold() {
        ProductCatalog catalog = thereIsProductCatalog();
        String productId = catalog.addProduct("lego set 8083", "nice one");

        List<Product> publishedProducts = catalog.allPublishedProducts();
        assertEquals(0, publishedProducts.size());
    }

    @Test
    void publicationIsPossibleWhenPriceAndImageAreDefined() {
        ProductCatalog catalog = thereIsProductCatalog();
        String productId = catalog.addProduct("lego set 8083", "nice one");

        assertThrows(
                ProductCantBePublishedException.class,
                () -> catalog.publishProduct(productId)
        );


    }

    private ProductCatalog thereIsProductCatalog() {
        return new ProductCatalog(
                new DbProductStorage()
        );
    }
    private void assertListIsEmpty(List<Product> products) {
        assert 0 == products.size();
    }

}
