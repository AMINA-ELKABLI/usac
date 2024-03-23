package ma.youcode.usac_last.usac.model.dto.Response;

import ma.youcode.usac_last.usac.model.entities.Child;
import ma.youcode.usac_last.usac.model.entities.Equip;

import java.util.List;

public record EquipResponseDTO(
        Long id,
        String name,
        String description,
        List<Child> children
) {
}
