package ma.youcode.usac_last.mapper;

import ma.youcode.usac_last.dto.ChildCreateUpdateDTO;
import ma.youcode.usac_last.dto.PlayCreateUpdateDTO;
import ma.youcode.usac_last.dto.Response.ChildResponseDTO;
import ma.youcode.usac_last.dto.Response.PlayResponseDTO;
import ma.youcode.usac_last.entities.Child;
import ma.youcode.usac_last.entities.Play;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface PlayMapper {
    Play toEntity(PlayCreateUpdateDTO playDto);
    PlayResponseDTO toDTO(Play play);

    @Mapping(target = "id", ignore = true)
    Play playCreateUpdateDTOToPlay(PlayCreateUpdateDTO createUpdateDTO);
}
