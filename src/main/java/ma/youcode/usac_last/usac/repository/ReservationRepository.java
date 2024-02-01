package ma.youcode.usac_last.usac.repository;

import ma.youcode.usac_last.usac.model.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByPlay_IdAndReservationDate(Long playId, LocalDateTime reservationDate);
}
