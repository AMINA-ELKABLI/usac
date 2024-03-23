package ma.youcode.usac_last.usac.service.impl;

import lombok.AllArgsConstructor;
import ma.youcode.usac_last.usac.exception.ResourceNotFoundException;
import ma.youcode.usac_last.usac.model.entities.Equip;
import ma.youcode.usac_last.usac.model.entities.Match;
import ma.youcode.usac_last.usac.repository.EquipRepository;
import ma.youcode.usac_last.usac.repository.MatchRepository;
import ma.youcode.usac_last.usac.service.IMatchService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.*;
@Service
@AllArgsConstructor
public class MatchServiceImpl implements IMatchService {
    private final MatchRepository matchRepository;
    private final EquipRepository equipRepository;
    @Override
    public Match saveMatch(Match match, Long equipOne, Long equipTwo) {
        Equip teamOne = equipRepository.findById(equipOne)
                .orElseThrow(() -> new ResourceNotFoundException("Equip not found with id: " + equipOne));
        Equip teamTwo = equipRepository.findById(equipTwo)
                .orElseThrow(() -> new ResourceNotFoundException("Equip not found with id: " + equipTwo));
        LocalDate currentDate = LocalDate.now();
        LocalDate matchDate = match.getMatchDate().toLocalDate();
        if (!matchDate.isAfter(currentDate)) {
            throw new IllegalStateException("The match date must be in the future and not the same day as today.");
        }
        List<Match> teamOneMatches = matchRepository.findByEquipsContainsAndMatchDate(teamOne, match.getMatchDate());
        List<Match> teamTwoMatches = matchRepository.findByEquipsContainsAndMatchDate(teamTwo, match.getMatchDate());
        if (!teamOneMatches.isEmpty() || !teamTwoMatches.isEmpty()) {
            throw new IllegalStateException("One or both teams are already scheduled for a match on this date.");
        }
        match.setEquips(Set.of(teamOne, teamTwo));
        return matchRepository.save(match);
    }

    @Override
    public Page<Match> getAllMatch(Pageable pageable) {
        return matchRepository.findAll(pageable);
    }

    @Override
    public Optional<Match> getMatchByName(String name) {
        return matchRepository.findByName(name);
    }

    @Override
    public void deleteMatch(Long id) {
        if (!matchRepository.existsById(id)) {
            throw new ResourceNotFoundException("Match not found with id: " + id);
        }
        matchRepository.deleteById(id);
    }
    @Override
    public Match updateMatch(Long id, Match match) {
        Match existingMatch = matchRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Match not found with id: " + id));
        existingMatch.setName(match.getName());
        existingMatch.setMatchDate(match.getMatchDate());
        existingMatch.setLocation(match.getLocation());
        existingMatch.setType(match.getType());
        return matchRepository.save(existingMatch);
    }
    @Override
    public long getTotalMatch() {
        return matchRepository.count();
    }
    @Override
    public Optional<Match> getMatchById(Long id) {
        return matchRepository.findById(id);
    }


}
