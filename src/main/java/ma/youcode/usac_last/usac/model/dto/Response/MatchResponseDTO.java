package ma.youcode.usac_last.usac.model.dto.Response;

public record MatchResponseDTO<LocalDateTime>(
        Long id,
        String name,
        LocalDateTime matchDate,
        String location,
        String type) {
}
