package stockchecker.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static stockchecker.TestUtils.getProduct;

public class StockTest {

    @Test
    public void testCreateStock() {
        Product product = getProduct();
        Stock stock = new Stock(product);
        assertEquals(product.getId(), stock.getId());
        assertEquals(1, stock.getAmount());
        assertEquals(true, stock.isInStock());
    }

    @Test
    public void testSetAmount() {
        Product product = getProduct();
        Stock stock = new Stock(product);
        stock.setAmount(5);

        assertEquals(5, stock.getAmount());
    }

    @Test
    public void testSetAmountToZero() {
        Product product = getProduct();
        Stock stock = new Stock(product);
        stock.setAmount(0);

        assertEquals(0, stock.getAmount());
        assertEquals(false, stock.isInStock());
    }
}
