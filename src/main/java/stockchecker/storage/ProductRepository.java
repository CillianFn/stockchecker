package stockchecker.storage;

import org.springframework.data.mongodb.repository.MongoRepository;
import stockchecker.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
}
