package stockchecker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stockchecker.model.Product;
import stockchecker.model.Stock;
import stockchecker.service.ProductService;
import stockchecker.service.StockService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class StockController {

    @Autowired
    private StockService stockService;

    @Autowired
    private ProductService productService;

    @GetMapping("/stock/{id}")
    public ResponseEntity<?> getStock(@PathVariable String id) {
        return ResponseEntity.of(stockService.getStock(id));
    }

    @PatchMapping("/stock/{id}")
    public ResponseEntity<?> patchStock(@PathVariable String id, @RequestBody Stock stock) {
        Optional<Product> product = productService.getProduct(id);

        if(!product.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            stock.setProduct(product.get());
        }

        Stock patchedStock = stockService.patchStock(stock);
        return new ResponseEntity<>(patchedStock, HttpStatus.ACCEPTED);
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
