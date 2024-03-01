package ma.youcode.usac_last.usac.service.impl;

import lombok.AllArgsConstructor;
import ma.youcode.usac_last.usac.model.entities.Stock;
import ma.youcode.usac_last.usac.repository.StockRepository;
import ma.youcode.usac_last.usac.service.IStockService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class StockServiceImpl implements IStockService {
    private final StockRepository stockRepository;
    @Override
    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

    @Override
    public Optional<Stock> getStockById(Long id) {
        return Optional.empty();
    }

    @Override
    public Stock createStock(Stock stock) {

        stock.setId(null);
        if (stock.getQuantity() <= 0) {
            throw new IllegalArgumentException("La quantité doit être positive");
        }
        Stock savedStock = stockRepository.save(stock);
        if (savedStock != null) {
            return savedStock;
        } else {
            throw new RuntimeException("Échec de la création du stock");
        }
    }


    @Override
    public Stock updateStock(Long id, Stock stock) {

        return null;
    }

    @Override
    public void deleteStock(Long id) {
        stockRepository.findById(id).ifPresent(stock -> stockRepository.delete(stock));

    }
}
