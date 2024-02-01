package ma.youcode.usac_last.usac.mapper;

import ma.youcode.usac_last.usac.model.dto.ReservationCreateUpdateDTO;
import ma.youcode.usac_last.usac.model.dto.Response.ReservationResponseDTO;
import ma.youcode.usac_last.usac.model.entities.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface ReservationMapper {
    Reservation toEntity(ReservationCreateUpdateDTO reservationDto);
    ReservationResponseDTO toDTO(Reservation reservation);

    @Mapping(target = "id", ignore = true)
    Reservation reservationCreateUpdateDTOToPlay(ReservationCreateUpdateDTO createUpdateDTO);
}
