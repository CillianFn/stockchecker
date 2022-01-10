package stockchecker.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StockTest {

    @Test
    public void testCreateStock() {
        Product product = new Product("test-1234", "Filter Coffee - 250g", "Single Origin");
        Stock stock = new Stock(product);
        assertEquals(product.getId(), stock.getId());
        assertEquals(1, stock.getAmount());
        assertEquals(true, stock.isInStock());
    }

    @Test
    public void testSetAmount() {
        Product product = new Product("test-1234", "Filter Coffee - 250g", "Single Origin");
        Stock stock = new Stock(product);
        stock.setAmount(5);

        assertEquals(5, stock.getAmount());
    }

    @Test
    public void testSetAmountToZero() {
        Product product = new Product("test-1234", "Filter Coffee - 250g", "Single Origin");
        Stock stock = new Stock(product);
        stock.setAmount(0);

        assertEquals(0, stock.getAmount());
        assertEquals(false, stock.isInStock());
    }
}
