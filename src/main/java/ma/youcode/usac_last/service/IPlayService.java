package ma.youcode.usac_last.service;

import ma.youcode.usac_last.entities.Child;
import ma.youcode.usac_last.entities.Play;

import java.util.List;
import java.util.Optional;

public interface IPlayService {
    Play savePlay(Play play);
    List<Play> getAllPlayies();
    Optional<Play> getPlaydById(Long id);
    void deletePlay(Long id);
}
