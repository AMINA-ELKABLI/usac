package ma.youcode.usac_last.dto.Response;

import ma.youcode.usac_last.enums.MaterialCondition;

public record StockResponseDTO(
        Long id,
        String materialName,
        String description,
        int quantity,
        MaterialCondition condition
) {
}
