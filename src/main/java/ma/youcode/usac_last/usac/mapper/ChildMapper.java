package ma.youcode.usac_last.usac.mapper;

import ma.youcode.usac_last.usac.model.dto.ChildCreateUpdateDTO;
import ma.youcode.usac_last.usac.model.dto.Response.ChildResponseDTO;
import ma.youcode.usac_last.usac.model.entities.Child;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface ChildMapper {
    Child toEntity(ChildCreateUpdateDTO childDto);

    @Mapping(source = "status", target = "status")
    ChildResponseDTO toDTO(Child child);


}
