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
        // Assurez-vous que l'ID du stock est nul pour qu'il soit considéré comme un nouvel enregistrement
        stock.setId(null);

        // Vérifiez si la quantité est positive
        if (stock.getQuantity() <= 0) {
            throw new IllegalArgumentException("La quantité doit être positive");
        }

        // Utilisez le stockRepository pour enregistrer le stock dans la base de données
        Stock savedStock = stockRepository.save(stock);

        // Vérifiez si l'enregistrement a été correctement sauvegardé
        if (savedStock != null) {
            return savedStock; // Renvoie l'objet Stock enregistré avec l'ID généré
        } else {
            throw new RuntimeException("Échec de la création du stock"); // Gestion de l'erreur si la sauvegarde échoue
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
