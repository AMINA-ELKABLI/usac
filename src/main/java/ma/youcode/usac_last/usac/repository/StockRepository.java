package ma.youcode.usac_last.usac.repository;

import ma.youcode.usac_last.usac.model.entities.Stock;
import ma.youcode.usac_last.usac.model.enums.MaterialCondition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long> {
    @Query("SELECT s FROM Stock s WHERE " +
            "(:materialName IS NULL OR s.materialName LIKE %:materialName%) AND " +
            "(:description IS NULL OR s.description LIKE %:description%) AND " +
            "(:condition IS NULL OR s.condition = :condition) AND " +
            "(:quantity IS NULL OR s.quantity = :quantity)")
    List<Stock> searchStocks(@Param("materialName") String materialName,
                             @Param("description") String description,
                             @Param("condition") MaterialCondition condition,
                             @Param("quantity") Integer quantity);
}
