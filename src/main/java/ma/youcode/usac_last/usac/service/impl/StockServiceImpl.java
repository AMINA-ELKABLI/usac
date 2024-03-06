package ma.youcode.usac_last.usac.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import ma.youcode.usac_last.usac.model.entities.Stock;
import ma.youcode.usac_last.usac.model.enums.MaterialCondition;
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
        return stockRepository.findById(id);
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

        Stock existingStock = stockRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Stock with id " + id + " not found"));

        existingStock.setMaterialName(stock.getMaterialName());
        existingStock.setDescription(stock.getDescription());
        existingStock.setCondition(stock.getCondition());
        existingStock.setQuantity(stock.getQuantity());

        return stockRepository.save(existingStock);
    }

    @Override
    public List<Stock> searchStocks(String materialName, String description, MaterialCondition condition, Integer quantity) {
        return stockRepository.searchStocks(materialName, description, condition, quantity);
    }
    @Override
    public void deleteStock(Long id) {
        Stock stock = stockRepository.findById(id).orElse(null);

        if (stock != null) {
            stockRepository.delete(stock);
        } else {
            throw new EntityNotFoundException("Stock with id " + id + " not found");
        }
    }

}
