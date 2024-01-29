package ma.youcode.usac_last.service.impl;

import lombok.AllArgsConstructor;
import ma.youcode.usac_last.entities.Reservation;
import ma.youcode.usac_last.exception.InvalidDataException;
import ma.youcode.usac_last.repository.ReservationRepository;
import ma.youcode.usac_last.service.IReservationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class ReservationServiceImpl implements IReservationService {
    private final ReservationRepository reservationRepository;
    @Override
    public Reservation saveReservation(Reservation reservation) {
        validateReservation(reservation);
        checkAvailability(reservation);
        return reservationRepository.save(reservation);
    }

    @Override
    public Optional<Reservation> getReservationById(Long id) {
        return reservationRepository.findById(id);
    }

    @Override
    public List<Reservation> getAllReservations() {

        return reservationRepository.findAll();
    }

    @Override
    public void deleteReservation(Long id) {
        Optional<Reservation> reservation = reservationRepository.findById(id);
        reservation.ifPresentOrElse(
                reservationRepository::delete,
                () -> {
                    throw new RuntimeException("Reservation not found with ID: " + id);
                }
        );

    }
    private void validateReservation(Reservation reservation) {
        if (reservation.getPlay() == null || reservation.getChild() == null) {
            throw new IllegalArgumentException("Play and Child are required for a reservation.");
        }

        if (reservation.getReservationDate() == null || reservation.getReservationDate().isBefore(LocalDateTime.now())) {
            throw new InvalidDataException("Reservation date is required and must be in the future.");
        }
    }

    private void checkAvailability(Reservation reservation) {
        List<Reservation> existingReservations = reservationRepository.findByPlay_IdAndReservationDate(
                reservation.getPlay().getId(),
                reservation.getReservationDate()
        );
        int maxCapacity = reservation.getPlay().getMaxCapacity();
        if (existingReservations.size() >= maxCapacity) {
            throw new IllegalStateException("The play is fully booked for this date.");
        }
        if (!existingReservations.isEmpty()) {
            throw new IllegalStateException("There are already reservations for this play at the given date.");
        }

    }

}
