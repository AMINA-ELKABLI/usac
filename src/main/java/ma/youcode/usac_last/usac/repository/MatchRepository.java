package ma.youcode.usac_last.usac.repository;

import ma.youcode.usac_last.usac.model.entities.Match;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MatchRepository extends JpaRepository<Match, Long> {
    long count();

    Optional<Match> findByName(String name);
}
