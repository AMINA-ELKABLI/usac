package ma.youcode.usac_last.usac.model.dto.Response;

import java.time.LocalDateTime;

public record PlayResponseDTO(
        Long id,
        String name,
        String description,
        LocalDateTime schedule,
        Long matchId,
        int maxCapacity
) {
}
