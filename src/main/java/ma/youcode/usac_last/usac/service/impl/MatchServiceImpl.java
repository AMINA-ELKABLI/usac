package ma.youcode.usac_last.usac.service.impl;

import lombok.AllArgsConstructor;
import ma.youcode.usac_last.usac.exception.ResourceNotFoundException;
import ma.youcode.usac_last.usac.model.entities.Match;
import ma.youcode.usac_last.usac.repository.MatchRepository;
import ma.youcode.usac_last.usac.service.IMatchService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class MatchServiceImpl implements IMatchService {
    private final MatchRepository matchRepository;
    @Override
    public Match saveMatch(Match match) {
        return matchRepository.save(match);
    }

    @Override
    public List<Match> getAllMatch() {
        return matchRepository.findAll();
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
    public Match updateMatch(Match match) {
        Match existingMatch = matchRepository.findById(match.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Match not found with id: " + match.getId()));

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
}
