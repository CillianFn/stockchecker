package stockchecker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import stockchecker.model.Product;
import stockchecker.model.Stock;
import stockchecker.service.StockService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class StockController {

    // TODO - update to follow same conventions as ProductController

    @Autowired
    private StockService stockService;

    @GetMapping("/stock/{id}")
    public ResponseEntity<?> getStock(@PathVariable String id) {
        return ResponseEntity.of(stockService.getStock(id));
    }

    @PatchMapping("/stock/{id}")
    public ResponseEntity<?> patchStock(@PathVariable String id, @RequestBody Stock stock) {
        Product product = new Product(id);
        stock.setProduct(product);

        Stock patchedStock = stockService.patchStock(stock);
        return ResponseEntity.accepted().body(patchedStock);
    }

    @GetMapping("/stock")
    public ResponseEntity<Map<String, Object>> getStock() {
        List<Stock> stock = stockService.getStock();

        if(stock == null || stock.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Map<String, Object> response = new HashMap<>();
        response.put("stock", stock);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
