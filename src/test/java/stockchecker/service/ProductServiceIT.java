package stockchecker.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import stockchecker.storage.ProductRepository;


@ExtendWith(MockitoExtension.class)
public class ProductServiceIT {

    @InjectMocks
    private ProductService productService;

    @MockBean
    private StockService stockService;

    @MockBean
    private ProductRepository productRepository;

    @AfterEach
    void cleanUpDatabase() {
        productRepository.deleteAll();
    }

    @Test
    public void testAddNewProduct() {

    }

    @Test
    public void testAddExistingProduct() {

    }

    @Test
    public void testGetProduct() {

    }

    @Test
    public void testGetProducts() {

    }

    @Test
    public void testDeleteProduct() {

    }
}
