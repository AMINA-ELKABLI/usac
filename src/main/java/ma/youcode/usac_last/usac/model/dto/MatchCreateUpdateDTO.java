package ma.youcode.usac_last.usac.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record MatchCreateUpdateDTO(
        @NotBlank(message = "Le nom ne peut pas être vide")
        @Size(max = 255, message = "La longueur du nom ne peut pas dépasser 255 caractères")
        String name,

        @NotNull(message = "La date du match ne peut pas être nulle")
        LocalDateTime matchDate,

        @NotBlank(message = "L'emplacement ne peut pas être vide")
        @Size(max = 255, message = "La longueur de l'emplacement ne peut pas dépasser 255 caractères")
        String location,

        @NotBlank(message = "Le type ne peut pas être vide")
        @Size(max = 255, message = "La longueur du type ne peut pas dépasser 255 caractères")
        String type
) {
}
