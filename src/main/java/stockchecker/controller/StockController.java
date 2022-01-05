package stockchecker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import stockchecker.model.Product;
import stockchecker.model.Stock;
import stockchecker.service.StockService;

import java.util.List;
import java.util.Optional;

@RestController
public class StockController {

    // TODO - update to follow same conventions as ProductController

    @Autowired
    private StockService stockService;

    @GetMapping("/stock/{id}")
    public Stock getStock(@PathVariable String id) {
        Optional<Stock> stock = stockService.getStock(id);

        //TODO Implement logic to check for null case, fail if not found with appropriate HTTP status code

        return stock.get();
    }

    @PatchMapping("/stock/{id}")
    public Stock patchStock(@PathVariable String id, @RequestBody Stock stock) {
        Product product = new Product(id);
        stock.setProduct(product);
        return stockService.patchStock(stock);
    }

    @GetMapping("/stock")
    public List<Stock> getStock() {

        //TODO check if list null or empty, fail if null or empty with appropriate HTTP status code
        return stockService.getStock();
    }
}
