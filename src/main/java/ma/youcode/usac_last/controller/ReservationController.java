package ma.youcode.usac_last.controller;

import lombok.AllArgsConstructor;
import ma.youcode.usac_last.dto.ReservationCreateUpdateDTO;
import ma.youcode.usac_last.dto.Response.ReservationResponseDTO;
import ma.youcode.usac_last.entities.Reservation;
import ma.youcode.usac_last.mapper.ReservationMapper;
import ma.youcode.usac_last.service.IReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/reservation")
@AllArgsConstructor
public class ReservationController {
    private final IReservationService reservationService;
    private final ReservationMapper reservationMapper;



    @PostMapping("/")
    public ResponseEntity<ReservationResponseDTO> createReservation(@RequestBody ReservationCreateUpdateDTO reservationDTO) {
        Reservation reservation = reservationMapper.toEntity(reservationDTO);
        Reservation savedReservation = reservationService.saveReservation(reservation);
        ReservationResponseDTO reservationResponseDTO = reservationMapper.toDTO(savedReservation);
        return new ResponseEntity<>(reservationResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationResponseDTO> getReservationById(@PathVariable Long id) {
        Reservation reservation = reservationService.getReservationById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found with ID: " + id));
        ReservationResponseDTO reservationResponseDTO = reservationMapper.toDTO(reservation);
        return new ResponseEntity<>(reservationResponseDTO, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<ReservationResponseDTO>> getAllReservations() {
        List<Reservation> reservations = reservationService.getAllReservations();
        List<ReservationResponseDTO> reservationResponseDTOs = reservations.stream()
                .map(reservationMapper::toDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(reservationResponseDTOs, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
