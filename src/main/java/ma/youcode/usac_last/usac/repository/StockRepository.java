package ma.youcode.usac_last.usac.repository;

import ma.youcode.usac_last.usac.model.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {
}
