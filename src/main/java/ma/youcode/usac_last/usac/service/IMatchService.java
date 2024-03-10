package ma.youcode.usac_last.usac.service;

import ma.youcode.usac_last.usac.model.entities.Equip;
import ma.youcode.usac_last.usac.model.entities.Match;

import java.util.List;
import java.util.Optional;

public interface IMatchService {
    Match saveMatch(Match match);
    List<Match> getAllMatch();
    Optional<Match> getMatchByName(String name);
    void deleteMatch(Long id);
    Match updateMatch(Match match);
    long getTotalMatch();
}
