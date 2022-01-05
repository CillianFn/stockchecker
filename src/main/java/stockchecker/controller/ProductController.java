package stockchecker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stockchecker.model.Product;
import stockchecker.service.ProductService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class ProductController {

    // TODO -
    //  should ResponseEntity contain status code and error message if not found rather than just empty

    @Autowired
    private ProductService productService;

    @RequestMapping("/")
    public String index() {
        // Used for dev
        return "Healthy";
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<?> getProduct(@PathVariable String id) {
        Optional<Product> product = productService.getProduct(id);

        if(!product.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.of(product);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<?> addProduct(@PathVariable String id, @RequestBody Product product) {
        product.setId(id);
        Product newProduct = productService.addProduct(product);
        return ResponseEntity.accepted().body(newProduct);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/products")
    public ResponseEntity<Map<String, Object>> getProducts(Pageable pageable) {
        Page<Product> products = productService.getProducts(pageable);


        // TODO - define appropriate paginated response
        Map<String, Object> response = new HashMap<>();
        response.put("items", products.getContent());
        response.put("pageSize", "");
        response.put("pageNumber", "");
        ResponseEntity.accepted().body("ayt hoe");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
