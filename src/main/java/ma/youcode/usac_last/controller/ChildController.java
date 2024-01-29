package ma.youcode.usac_last.controller;

import lombok.RequiredArgsConstructor;
import ma.youcode.usac_last.dto.ChildCreateUpdateDTO;
import ma.youcode.usac_last.dto.Response.ChildResponseDTO;
import ma.youcode.usac_last.entities.Child;
import ma.youcode.usac_last.exception.ResourceNotFoundException;
import ma.youcode.usac_last.mapper.ChildMapper;
import ma.youcode.usac_last.service.IChildService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/child")
@RequiredArgsConstructor
public class ChildController {
    private final IChildService childService;

    private final ChildMapper childMapper;
    @PostMapping
    public ResponseEntity<ChildResponseDTO> saveChild(@RequestBody ChildCreateUpdateDTO childCreateUpdateDTO) {
        Child child =  childMapper.toEntity(childCreateUpdateDTO);
        Child savedChild = childService.saveChild(child);
        ChildResponseDTO childResponseDTO =  childMapper.toDTO(savedChild);
        return ResponseEntity.status(HttpStatus.CREATED).body(childResponseDTO);
    }
    @GetMapping
    public ResponseEntity<List<ChildResponseDTO>> getAllChildren() {
        List<Child> children = childService.getAllChildren();
        List<ChildResponseDTO> childResponseDTOs = children.stream()
                .map(childMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(childResponseDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChildResponseDTO> getChildById(@PathVariable Long id) {
        Child child = childService.getChildById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enfant introuvable avec l'ID : " + id));
        ChildResponseDTO childResponseDTO = childMapper.toDTO(child);
        return ResponseEntity.ok(childResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteChild(@PathVariable Long id) {
        childService.deleteChild(id);
        return ResponseEntity.noContent().build();
    }

}
