package ma.youcode.usac_last.usac.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record EquipCreateUpdateDTO(
        @NotEmpty(message = "Le nom ne peut pas être vide")
        @Size(min = 3, max = 100, message = "Le nom doit contenir entre 3 et 100 caractères")
        String name,

        @NotEmpty(message = "La description ne peut pas être vide")
        @Size(min = 10, max = 500, message = "La description doit contenir entre 10 et 500 caractères")
        String description
) {
}
