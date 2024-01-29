package ma.youcode.usac_last.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record PlayCreateUpdateDTO(
        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Description is required")
        String description,

        @NotNull(message = "Schedule is required")
        LocalDateTime schedule,

        @NotNull(message = "Match ID is required")
        Long matchId,
        @Positive(message = "Max capacity must be a positive number")
        int maxCapacity


        ) {
}
