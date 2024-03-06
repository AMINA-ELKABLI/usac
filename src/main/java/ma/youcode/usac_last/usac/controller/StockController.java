package ma.youcode.usac_last.usac.controller;

import lombok.AllArgsConstructor;
import ma.youcode.usac_last.usac.model.dto.StockCreateUpdateDTO;
import ma.youcode.usac_last.usac.model.dto.Response.StockResponseDTO;
import ma.youcode.usac_last.usac.model.entities.Stock;
import ma.youcode.usac_last.usac.mapper.StockMapper;
import ma.youcode.usac_last.usac.model.enums.MaterialCondition;
import ma.youcode.usac_last.usac.service.IStockService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/stock")
@CrossOrigin(origins = "http://localhost:4201")

@AllArgsConstructor
public class StockController {
    private final IStockService stockService;
    private final StockMapper stockMapper;

    @GetMapping
    public ResponseEntity<Page<StockResponseDTO>> getAllStocks(@RequestParam(defaultValue = "0") int page,
                                                               @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<StockResponseDTO> response = stockService.getAllStocks(pageable)
                .map(stockMapper::toDTO);
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

    @GetMapping("/search")
    public ResponseEntity<List<StockResponseDTO>> searchStocks(@RequestParam(required = false) String materialName,
                                                               @RequestParam(required = false) String description,
                                                               @RequestParam(required = false) MaterialCondition condition,
                                                               @RequestParam(required = false) Integer quantity) {
        List<StockResponseDTO> response = stockService.searchStocks(materialName, description, condition, quantity)
                .stream()
                .map(stockMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

}
