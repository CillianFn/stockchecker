package stockchecker.storage;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import stockchecker.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
    Product findByName(String name);
    Page<Product> findAll(Pageable pageable);
}
