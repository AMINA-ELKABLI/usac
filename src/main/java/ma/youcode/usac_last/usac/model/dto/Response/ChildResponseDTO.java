package ma.youcode.usac_last.usac.model.dto.Response;

import java.time.LocalDate;

public record ChildResponseDTO(
        Long id,
        String name,
        LocalDate dateOfBirth,
        String gender,
        String address
) {
}
