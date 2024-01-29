package ma.youcode.usac_last.controller;

import lombok.AllArgsConstructor;
import ma.youcode.usac_last.dto.Response.StockResponseDTO;
import ma.youcode.usac_last.dto.StockCreateUpdateDTO;
import ma.youcode.usac_last.entities.Stock;
import ma.youcode.usac_last.mapper.StockMapper;
import ma.youcode.usac_last.service.IStockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/stock")
@AllArgsConstructor
public class StockController {
    private final IStockService stockService;
    private final StockMapper stockMapper;

    @GetMapping
    public ResponseEntity<List<StockResponseDTO>> getAllStocks() {
        List<StockResponseDTO> response = stockService.getAllStocks().stream()
                .map(stockMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockResponseDTO> getStockById(@PathVariable Long id) {
        StockResponseDTO response = stockService.getStockById(id)
                .map(stockMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Stock not found with id: " + id));
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<StockResponseDTO> createStock(@RequestBody StockCreateUpdateDTO stockDto) {
        Stock stock = stockMapper.toEntity(stockDto);
        Stock savedStock = stockService.createStock(stock);
        StockResponseDTO response = stockMapper.toDTO(savedStock);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StockResponseDTO> updateStock(@PathVariable Long id, @RequestBody StockCreateUpdateDTO stockDto) {
        Stock stock = stockMapper.toEntity(stockDto);
        Stock updatedStock = stockService.updateStock(id, stock);
        StockResponseDTO response = stockMapper.toDTO(updatedStock);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStock(@PathVariable Long id) {
        stockService.deleteStock(id);
        return ResponseEntity.ok().build();
    }
}
