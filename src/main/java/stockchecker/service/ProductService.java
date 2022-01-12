package stockchecker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stockchecker.model.Product;
import stockchecker.model.Stock;
import stockchecker.storage.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StockService stockService;

    public Optional<Product> getProduct(String id) {
        return productRepository.findById(id);
    }

    public Product addProduct(Product product) {
        Optional<Stock> stock = stockService.getStock(product.getId());
        if(!stock.isPresent()) {
            Stock newStock = new Stock(product);
            stockService.patchStock(newStock);
        } else {
            Stock updateStock = stock.get();
            int amount = updateStock.getAmount();
            updateStock.setAmount(++amount);
            stockService.patchStock(updateStock);
        }

        return productRepository.save(product);
    }

    public void deleteProduct(String id) throws Exception {
        Optional<Stock> stock = stockService.getStock(id);
        if(!stock.isPresent()) {
            return;
        } else {
            Stock updateStock = stock.get();
            int amount = updateStock.getAmount();
            if (amount == 0) {
                // TODO custom/meaningful exception, prevents Stock objects from having a negative amount or being deleted
                // Does this bubble up to controller and create a meaningful ResponsEntity?
                throw new Exception("This product has no stock");
            }
            updateStock.setAmount(--amount);
            stockService.patchStock(updateStock);
        }

        productRepository.deleteById(id);
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }
}
