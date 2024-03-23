package ma.youcode.usac_last.usac.model.dto.Response;

import ma.youcode.usac_last.usac.model.entities.Equip;
import ma.youcode.usac_last.usac.model.entities.Match;

import java.util.List;

public record MatchResponseDTO<LocalDateTime>(
        Long id,
        String name,
        LocalDateTime matchDate,
        String location,
        String type,
        List<Equip> equips
        ) {

}
