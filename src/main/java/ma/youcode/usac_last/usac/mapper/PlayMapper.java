package ma.youcode.usac_last.usac.mapper;

import ma.youcode.usac_last.usac.model.dto.PlayCreateUpdateDTO;
import ma.youcode.usac_last.usac.model.dto.Response.PlayResponseDTO;
import ma.youcode.usac_last.usac.model.entities.Play;
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
