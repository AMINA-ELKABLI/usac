package ma.youcode.usac_last.mapper;

import ma.youcode.usac_last.dto.PlayCreateUpdateDTO;
import ma.youcode.usac_last.dto.ReservationCreateUpdateDTO;
import ma.youcode.usac_last.dto.Response.PlayResponseDTO;
import ma.youcode.usac_last.dto.Response.ReservationResponseDTO;
import ma.youcode.usac_last.entities.Play;
import ma.youcode.usac_last.entities.Reservation;
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
