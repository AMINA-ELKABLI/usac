package ma.youcode.usac_last.mapper;

import ma.youcode.usac_last.dto.ChildCreateUpdateDTO;
import ma.youcode.usac_last.dto.Response.ChildResponseDTO;
import ma.youcode.usac_last.dto.Response.StockResponseDTO;
import ma.youcode.usac_last.dto.StockCreateUpdateDTO;
import ma.youcode.usac_last.entities.Child;
import ma.youcode.usac_last.entities.Stock;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface StockMapper {
    Stock toEntity(StockCreateUpdateDTO stockdDto);
    StockResponseDTO toDTO(Stock stock);

    @Mapping(target = "id", ignore = true)
    Stock stockCreateUpdateDTOToChild(StockCreateUpdateDTO createUpdateDTO);
}
