package ma.youcode.usac_last.usac.service.impl;

import lombok.AllArgsConstructor;
import ma.youcode.usac_last.usac.service.IPlayService;
import ma.youcode.usac_last.usac.model.entities.Play;
import ma.youcode.usac_last.usac.exception.InvalidDataException;
import ma.youcode.usac_last.usac.exception.ResourceAlreadyExistsException;
import ma.youcode.usac_last.usac.repository.PlayRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class PlayServiceImpl implements IPlayService {
    private final PlayRepository playRepository;
    @Override
    public Play savePlay(Play play) {
        validatePlay(play);

        try {
            return playRepository.save(play);
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to save the play due to database issues: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred while saving the play: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Play> getAllPlayies()
    {
        return playRepository.findAll();
    }

    @Override
    public Optional<Play> getPlaydById(Long id) {
        return Optional.empty();
    }

    @Override
    public void deletePlay(Long id) {

    }
    private void validatePlay(Play play) {
        if (play.getName() == null || play.getName().trim().isEmpty()) {
            throw new InvalidDataException("Play name is required and cannot be empty.");
        }
        playRepository.findByName(play.getName())
                .ifPresent(existingPlay -> {
                    throw new ResourceAlreadyExistsException("A play with the same name and date already exists.");
                });

    }
}
