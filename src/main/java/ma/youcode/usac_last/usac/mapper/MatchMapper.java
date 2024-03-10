package ma.youcode.usac_last.usac.mapper;

import ma.youcode.usac_last.usac.model.dto.EquipCreateUpdateDTO;
import ma.youcode.usac_last.usac.model.dto.MatchCreateUpdateDTO;
import ma.youcode.usac_last.usac.model.dto.Response.EquipResponseDTO;
import ma.youcode.usac_last.usac.model.dto.Response.MatchResponseDTO;
import ma.youcode.usac_last.usac.model.entities.Equip;
import ma.youcode.usac_last.usac.model.entities.Match;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface MatchMapper {
    Match toEntity(MatchCreateUpdateDTO equipDto);
    MatchResponseDTO toDTO(Match match);

    @Mapping(target = "id", ignore = true)
    Match matchCreateUpdateDTOToPlay(MatchCreateUpdateDTO createUpdateDTO);
}
