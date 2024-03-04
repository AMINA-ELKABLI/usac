package ma.youcode.usac_last.usac.service;

import ma.youcode.usac_last.usac.model.entities.Stock;
import ma.youcode.usac_last.usac.model.enums.MaterialCondition;

import java.util.List;
import java.util.Optional;

public interface IStockService {
    List<Stock> getAllStocks();

    Optional<Stock> getStockById(Long id);

    Stock createStock(Stock stock);

    Stock updateStock(Long id, Stock stock);

    void deleteStock(Long id);
    List<Stock> searchStocks(String materialName, String description, MaterialCondition condition, Integer quantity);
}
