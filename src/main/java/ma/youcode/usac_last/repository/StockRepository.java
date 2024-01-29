package ma.youcode.usac_last.repository;

import ma.youcode.usac_last.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {
}
