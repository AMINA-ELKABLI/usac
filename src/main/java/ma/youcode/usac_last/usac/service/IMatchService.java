package ma.youcode.usac_last.usac.service;

import ma.youcode.usac_last.usac.model.entities.Equip;
import ma.youcode.usac_last.usac.model.entities.Match;
import ma.youcode.usac_last.usac.model.entities.Stock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IMatchService {
    Match saveMatch(Match match);
    Page<Match> getAllMatch(Pageable pageable);
    Optional<Match> getMatchByName(String name);
    void deleteMatch(Long id);
    Match updateMatch(Match match);
    long getTotalMatch();
}
