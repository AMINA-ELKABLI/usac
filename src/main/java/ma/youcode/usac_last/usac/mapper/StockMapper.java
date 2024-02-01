package ma.youcode.usac_last.usac.mapper;

import ma.youcode.usac_last.usac.model.dto.StockCreateUpdateDTO;
import ma.youcode.usac_last.usac.model.entities.Stock;
import ma.youcode.usac_last.usac.model.dto.Response.StockResponseDTO;
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
