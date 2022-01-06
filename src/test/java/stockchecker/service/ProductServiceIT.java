package stockchecker.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import stockchecker.model.Product;
import stockchecker.storage.ProductRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataMongoTest
@ExtendWith(MockitoExtension.class)
public class ProductServiceIT {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @AfterEach
    void cleanUpDatabase() {
        productRepository.deleteAll();
    }

    @Test
    public void test() {
        Product expectedProduct = new Product("test-id-1234", "Filter Coffee 250g", "Single Origin");
        productRepository.save(expectedProduct);
        productService.addProduct(expectedProduct);
        List<Product> productList = productService.getProducts();
        for(Product p : productList) {
            System.out.println(p);
        }
        Optional<Product> actualProduct = productService.getProduct(expectedProduct.getId());
        assertEquals(expectedProduct, actualProduct.get());
    }
}
