package ma.youcode.usac_last.usac.repository;

import ma.youcode.usac_last.usac.model.entities.Play;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayRepository  extends JpaRepository<Play, Long> {
    Optional<Play> findByName(String name);
}
