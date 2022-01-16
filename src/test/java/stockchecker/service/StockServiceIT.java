package stockchecker.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import stockchecker.model.Product;
import stockchecker.model.Stock;
import stockchecker.storage.StockRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static stockchecker.TestUtils.getProduct;

@ExtendWith(MockitoExtension.class)
public class StockServiceIT {

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private StockService stockService;

    @Test
    public void testGetStock() {
        Product product = getProduct();
        Stock expectedStock = new Stock(product);

        given(stockRepository.findById(expectedStock.getId())).willReturn(Optional.of(expectedStock));

        Optional<Stock> actualStock = stockService.getStock(expectedStock.getId());

        assertEquals(expectedStock, actualStock.get());
    }

    @Test
    public void testGetMultipleStock() {
        Product product = getProduct();
        Product product1 = new Product("test-1235", "Filter Coffee - 250g", "Blend");
        Product product2 = new Product("test-1236", "Filter Coffee - 1000g", "Blend");

        Stock expectedStock = new Stock(product);
        Stock expectedStock1 = new Stock(product1);
        Stock expectedStock2 = new Stock(product2);

        List<Stock> expectedStockList = new ArrayList<>(Arrays.asList(expectedStock, expectedStock1, expectedStock2));

        given(stockRepository.findAll()).willReturn(expectedStockList);

        List<Stock> actualStock = stockService.getStock();

        assertEquals(expectedStockList, actualStock);
    }

    @Test
    public void testPatchStock() {
        Product product = getProduct();
        Stock expectedStock = new Stock(product);

        given(stockRepository.save(expectedStock)).willReturn(expectedStock);

        Stock savedStock = stockService.patchStock(expectedStock);

        assertThat(savedStock).isNotNull();
        verify(stockRepository).save(any(Stock.class));
    }
}
