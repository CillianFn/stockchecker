package stockchecker.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import stockchecker.model.Product;
import stockchecker.model.Stock;
import stockchecker.storage.ProductRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class ProductServiceIT {

    @Mock
    private StockService stockService;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    public void testAddNewProduct() {
        Product product = new Product("test-1234", "Filter Coffee - 250g", "Single Origin");
        Stock newStock = new Stock(product);

        given(productRepository.save(product)).willReturn(product);

        Product savedProduct = productService.addProduct(product);

        assertThat(savedProduct).isNotNull();
        verify(stockService).patchStock(eq(newStock));
        verify(productRepository).save(any(Product.class));
    }

    @Test
    public void testAddExistingProduct() {
        Product product = new Product("test-1234", "Filter Coffee - 250g", "Single Origin");
        Stock existingStock = new Stock(product);
        existingStock.setAmount(2);

        given(productRepository.save(product)).willReturn(product);
        given(stockService.getStock(product.getId())).willReturn(Optional.of(existingStock));

        Product savedProductDuplicate = productService.addProduct(product);

        assertThat(savedProductDuplicate).isNotNull();
        verify(stockService).patchStock(eq(existingStock));
        verify(productRepository).save(any(Product.class));
    }

    @Test
    public void testGetProduct() {
        Product expectedProduct = new Product("test-1234", "Filter Coffee - 250g", "Single Origin");

        given(productRepository.findById(expectedProduct.getId())).willReturn(Optional.of(expectedProduct));

        Optional<Product> actualProduct = productService.getProduct(expectedProduct.getId());

        assertEquals(expectedProduct, actualProduct.get());
    }

    @Test
    public void testGetProducts() {
        Product expectedProduct = new Product("test-1234", "Filter Coffee - 250g", "Single Origin");
        Product expectedProduct1 = new Product("test-1235", "Filter Coffee - 250g", "Blend");
        Product expectedProduct2 = new Product("test-1236", "Filter Coffee - 1000g", "Blend");
        List<Product> expectedProducts = new ArrayList<>(Arrays.asList(expectedProduct, expectedProduct1, expectedProduct2));

        given(productRepository.findAll()).willReturn(expectedProducts);

        List<Product> actualProducts = productService.getProducts();

        assertEquals(expectedProducts, actualProducts);
    }

    @Test
    public void testDeleteNonExistentProduct() throws Exception {
        productService.deleteProduct("test-1234");

        verify(productRepository, never()).deleteById("test-1234");
        verify(stockService, never()).patchStock(any(Stock.class));
    }

    @Test
    public void testDeleteProduct() throws Exception {
        Product product = new Product("test-1234", "Filter Coffee - 250g", "Single Origin");
        Stock existingStock = new Stock(product);

        given(stockService.getStock(existingStock.getId())).willReturn(Optional.of(existingStock));

        productService.deleteProduct(existingStock.getId());

        verify(productRepository).deleteById(existingStock.getId());
        verify(stockService).patchStock(any(Stock.class));
    }

    @Test
    public void testDeleteProductNotInStock() throws Exception {
        Product product = new Product("test-1234", "Filter Coffee - 250g", "Single Origin");
        Stock existingStock = new Stock(product);
        existingStock.setAmount(0);

        given(stockService.getStock(existingStock.getId())).willReturn(Optional.of(existingStock));

        assertThrows(Exception.class, () -> {
            productService.deleteProduct(existingStock.getId());
        });

        verify(productRepository, never()).deleteById(existingStock.getId());
        verify(stockService, never()).patchStock(any(Stock.class));
    }
}
