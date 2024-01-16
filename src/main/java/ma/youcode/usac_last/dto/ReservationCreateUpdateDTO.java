package ma.youcode.usac_last.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ReservationCreateUpdateDTO(
        @NotNull(message = "Child ID is required")
        Long childId,

        @NotNull(message = "Play ID is required")
        Long playId,

        @NotNull(message = "Reservation date is required")
        LocalDateTime reservationDate,

        @NotBlank(message = "Status is required")
        String status
) {
}
