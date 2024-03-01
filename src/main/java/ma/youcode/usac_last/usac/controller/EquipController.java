package ma.youcode.usac_last.usac.controller;

import lombok.RequiredArgsConstructor;
import ma.youcode.usac_last.usac.exception.ResourceNotFoundException;
import ma.youcode.usac_last.usac.mapper.EquipMapper;
import ma.youcode.usac_last.usac.model.dto.EquipCreateUpdateDTO;
import ma.youcode.usac_last.usac.model.dto.Response.EquipResponseDTO;
import ma.youcode.usac_last.usac.model.entities.Equip;
import ma.youcode.usac_last.usac.service.IEquipService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/equip")
@RequiredArgsConstructor
public class EquipController {
    private final IEquipService  equipService;
    private final EquipMapper equipMapper;
    @PostMapping
    public ResponseEntity<EquipResponseDTO> saveEquip(@RequestBody @Validated EquipCreateUpdateDTO equipCreateUpdateDTO) {
        Equip equip = equipMapper.toEntity(equipCreateUpdateDTO);
        Equip savedEquip = equipService.saveEquip(equip);
        EquipResponseDTO equipResponseDTO = equipMapper.toDTO(savedEquip);
        return ResponseEntity.status(HttpStatus.CREATED).body(equipResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<EquipResponseDTO>> getAllEquips() {
        List<Equip> equips = equipService.getAllEquips();
        List<EquipResponseDTO> equipResponseDTOs = equips.stream()
                .map(equipMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(equipResponseDTOs);
    }

    @GetMapping("/{name}")
    public ResponseEntity<EquipResponseDTO> getEquipByName(@PathVariable String name) {
        Equip equip = equipService.getEquipByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Équipement introuvable avec le nom : " + name));
        EquipResponseDTO equipResponseDTO = equipMapper.toDTO(equip);
        return ResponseEntity.ok(equipResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEquip(@PathVariable Long id) {
        equipService.deleteEquip(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EquipResponseDTO> updateEquip(@PathVariable Long id, @RequestBody @Validated EquipCreateUpdateDTO equipCreateUpdateDTO) {
        Equip equip = equipMapper.toEntity(equipCreateUpdateDTO);
        equip.setId(id);
        Equip updatedEquip = equipService.updateEquip(equip);
        EquipResponseDTO equipResponseDTO = equipMapper.toDTO(updatedEquip);
        return ResponseEntity.ok(equipResponseDTO);
    }
}
