package ma.youcode.usac_last.usac.model.dto.Response;


import ma.youcode.usac_last.usac.model.enums.MaterialCondition;

public record StockResponseDTO(
        Long id,
        String materialName,
        String description,
        int quantity,
        MaterialCondition condition
) {
}
