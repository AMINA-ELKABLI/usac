package ma.youcode.usac_last.usac.controller;

import lombok.AllArgsConstructor;
import ma.youcode.usac_last.usac.exception.ResourceNotFoundException;
import ma.youcode.usac_last.usac.mapper.MatchMapper;
import ma.youcode.usac_last.usac.model.dto.EquipCreateUpdateDTO;
import ma.youcode.usac_last.usac.model.dto.MatchCreateUpdateDTO;
import ma.youcode.usac_last.usac.model.dto.Response.EquipResponseDTO;
import ma.youcode.usac_last.usac.model.dto.Response.MatchResponseDTO;
import ma.youcode.usac_last.usac.model.entities.Equip;
import ma.youcode.usac_last.usac.model.entities.Match;
import ma.youcode.usac_last.usac.service.IMatchService;
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


}
