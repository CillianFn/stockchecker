package stockchecker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stockchecker.model.Stock;
import stockchecker.storage.StockRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    public List<Stock> getStock() {
        return stockRepository.findAll();
    }

    public Optional<Stock> getStock(String id) {
        return stockRepository.findById(id);
    }

    public Stock patchStock(Stock stock) {
        // TODO see if anything else is needed here, does patch work out of the box if correct object/ID is specified
        return stockRepository.save(stock);
    }
}
