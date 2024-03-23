package ma.youcode.usac_last.usac.service;

import ma.youcode.usac_last.usac.model.entities.Match;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IMatchService {
    Match saveMatch(Match match,Long equipOne, Long equipTwo);
    Page<Match> getAllMatch(Pageable pageable);
    Optional<Match> getMatchByName(String name);
    void deleteMatch(Long id);
    Match updateMatch(Long id, Match match);
    long getTotalMatch();
    Optional<Match> getMatchById(Long id);



}
