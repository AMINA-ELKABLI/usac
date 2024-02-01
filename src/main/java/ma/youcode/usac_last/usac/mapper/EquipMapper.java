package ma.youcode.usac_last.usac.mapper;

import ma.youcode.usac_last.usac.model.dto.EquipCreateUpdateDTO;
import ma.youcode.usac_last.usac.model.dto.PlayCreateUpdateDTO;
import ma.youcode.usac_last.usac.model.dto.Response.EquipResponseDTO;
import ma.youcode.usac_last.usac.model.dto.Response.PlayResponseDTO;
import ma.youcode.usac_last.usac.model.entities.Equip;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface EquipMapper {
    Equip toEntity(EquipCreateUpdateDTO equipDto);
    EquipResponseDTO toDTO(Equip equip);

    @Mapping(target = "id", ignore = true)
    Equip playCreateUpdateDTOToPlay(EquipCreateUpdateDTO createUpdateDTO);
}
