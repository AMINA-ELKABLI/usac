package ma.youcode.usac_last.mapper;

import ma.youcode.usac_last.dto.ChildCreateUpdateDTO;
import ma.youcode.usac_last.dto.Response.ChildResponseDTO;
import ma.youcode.usac_last.entities.Child;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface ChildMapper {
    Child toEntity(ChildCreateUpdateDTO childDto);
    ChildResponseDTO toDTO(Child child);

    @Mapping(target = "id", ignore = true)
    Child childCreateUpdateDTOToChild(ChildCreateUpdateDTO createUpdateDTO);

}
