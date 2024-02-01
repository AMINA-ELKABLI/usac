package ma.youcode.usac_last.usac.service;

import ma.youcode.usac_last.usac.model.entities.Reservation;

import java.util.List;
import java.util.Optional;

public interface IReservationService {
    Reservation saveReservation(Reservation reservation);
    Optional<Reservation> getReservationById(Long id);
    List<Reservation> getAllReservations();
    void deleteReservation(Long id);
}
