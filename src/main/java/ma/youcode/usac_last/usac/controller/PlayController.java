package ma.youcode.usac_last.usac.controller;

import lombok.AllArgsConstructor;
import ma.youcode.usac_last.usac.service.IPlayService;
import ma.youcode.usac_last.usac.model.dto.PlayCreateUpdateDTO;
import ma.youcode.usac_last.usac.model.dto.Response.PlayResponseDTO;
import ma.youcode.usac_last.usac.model.entities.Play;
import ma.youcode.usac_last.usac.mapper.PlayMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/play")
@AllArgsConstructor
public class PlayController {
    private final IPlayService playService;
    private final PlayMapper playMapper;
    @PostMapping("/")
    public ResponseEntity<PlayResponseDTO> createPlay(@RequestBody PlayCreateUpdateDTO playDTO) {
        Play play = playMapper.toEntity(playDTO);
        Play savedPlay = playService.savePlay(play);
        PlayResponseDTO playResponseDTO = playMapper.toDTO(savedPlay);
        return new ResponseEntity<>(playResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayResponseDTO> getPlayById(@PathVariable Long id) {
        Play play = playService.getPlaydById(id)
                .orElseThrow(() -> new RuntimeException("Play not found with ID: " + id));
        PlayResponseDTO playResponseDTO = playMapper.toDTO(play);
        return new ResponseEntity<>(playResponseDTO, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<PlayResponseDTO>> getAllPlays() {
        List<Play> plays = playService.getAllPlayies();
        List<PlayResponseDTO> playResponseDTOs = plays.stream()
                .map(playMapper::toDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(playResponseDTOs, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePlay(@PathVariable Long id) {
        playService.deletePlay(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
