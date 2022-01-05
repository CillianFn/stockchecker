package stockchecker.storage;

import org.springframework.data.mongodb.repository.MongoRepository;
import stockchecker.model.Stock;

public interface StockRepository extends MongoRepository<Stock, String> {
}
