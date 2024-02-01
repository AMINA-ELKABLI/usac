package ma.youcode.usac_last.usac.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import ma.youcode.usac_last.usac.model.enums.MaterialCondition;


public record StockCreateUpdateDTO(
        @NotEmpty(message = "Le nom du matériel ne peut pas être vide")
        String materialName,

        @NotEmpty(message = "La description ne peut pas être vide")
        String description,

        @Min(value = 1, message = "La quantité doit être au moins 1")
        int quantity,

        @NotNull(message = "La condition du matériel doit être spécifiée")
        MaterialCondition condition
) {
}
