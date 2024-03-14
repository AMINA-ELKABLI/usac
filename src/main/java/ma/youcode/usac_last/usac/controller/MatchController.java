package ma.youcode.usac_last.usac.controller;

import lombok.AllArgsConstructor;
import ma.youcode.usac_last.usac.exception.ResourceNotFoundException;
import ma.youcode.usac_last.usac.mapper.MatchMapper;
import ma.youcode.usac_last.usac.model.dto.EquipCreateUpdateDTO;
import ma.youcode.usac_last.usac.model.dto.MatchCreateUpdateDTO;
import ma.youcode.usac_last.usac.model.dto.Response.EquipResponseDTO;
import ma.youcode.usac_last.usac.model.dto.Response.MatchResponseDTO;
import ma.youcode.usac_last.usac.model.dto.Response.StockResponseDTO;
import ma.youcode.usac_last.usac.model.dto.StockCreateUpdateDTO;
import ma.youcode.usac_last.usac.model.entities.Equip;
import ma.youcode.usac_last.usac.model.entities.Match;
import ma.youcode.usac_last.usac.model.entities.Stock;
import ma.youcode.usac_last.usac.service.IMatchService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/match")
@AllArgsConstructor
public class MatchController {
    private final IMatchService matchService;
    private final MatchMapper matchMapper;

    @PostMapping
    public ResponseEntity<MatchResponseDTO> saveMatch(@RequestBody @Validated MatchCreateUpdateDTO matchCreateUpdateDTO) {
        Match match = matchMapper.toEntity(matchCreateUpdateDTO);
        Match savedMatch = matchService.saveMatch(match);
        MatchResponseDTO matchResponseDTO = matchMapper.toDTO(savedMatch);
        return ResponseEntity.status(HttpStatus.CREATED).body(matchResponseDTO);
    }

    @GetMapping
    public ResponseEntity<Page<MatchResponseDTO>> getAllMatch(@RequestParam(defaultValue = "0") int page,
                                                               @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<MatchResponseDTO> response = matchService.getAllMatch(pageable)
                .map(matchMapper::toDTO);
        return ResponseEntity.ok(response);
    }

  /*  @GetMapping("/{id}")
    public ResponseEntity<MatchResponseDTO> getMatchById(@PathVariable Long id) {
        MatchResponseDTO response = matchService.getMatchById(id)
                .map(matchMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Match not found with id: " + id));
        return ResponseEntity.ok(response);
    }



    @PutMapping("/{id}")
    public ResponseEntity<MatchResponseDTO> updateMatch(@PathVariable Long id, @RequestBody MatchCreateUpdateDTO matchDto) {
        Match match = matchMapper.toEntity(matchDto);
        Match updatedMatch = matchService.updateMatch(id, match);
        MatchResponseDTO response = matchMapper.toDTO(updatedMatch);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatch(@PathVariable Long id) {
        matchService.deleteMatch(id);
        return ResponseEntity.ok().build();
    }

   */


}
