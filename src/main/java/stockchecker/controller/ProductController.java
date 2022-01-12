package stockchecker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import stockchecker.model.Product;
import stockchecker.service.ProductService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product/{id}")
    public ResponseEntity<?> getProduct(@PathVariable String id) {
        return ResponseEntity.of(productService.getProduct(id));
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<?> addProduct(@PathVariable String id, @RequestBody Product product) {
        product.setId(id);
        Product newProduct = productService.addProduct(product);
        return ResponseEntity.accepted().body(newProduct);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable String id) throws Exception {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/products")
    public ResponseEntity<Map<String, Object>> getProducts() {
        List<Product> products = productService.getProducts();

        Map<String, Object> response = new HashMap<>();
        response.put("products", products);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
