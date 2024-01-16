package ma.youcode.usac_last.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record ChildCreateUpdateDTO(
        @NotBlank(message = "Name is required")
        String name,

        @NotNull(message = "Date of birth is required")
        LocalDate dateOfBirth,

        @NotBlank(message = "Gender is required")
        String gender,

        @NotBlank(message = "Address is required")
        String address,

        @NotBlank(message = "Guardian name is required")
        String guardianName,

        @Pattern(regexp = "^(\\+\\d{1,3}[- ]?)?\\d{10}$", message = "Invalid guardian contact")
        String guardianContact,

        @NotNull(message = "Equip ID is required")
        Long equipId
) {
}
