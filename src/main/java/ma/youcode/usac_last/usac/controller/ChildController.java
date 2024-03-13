package ma.youcode.usac_last.usac.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.youcode.usac_last.usac.mapper.ChildMapper;
import ma.youcode.usac_last.usac.model.dto.ChildCreateUpdateDTO;
import ma.youcode.usac_last.usac.model.dto.Response.ChildResponseDTO;
import ma.youcode.usac_last.usac.model.dto.Response.StockResponseDTO;
import ma.youcode.usac_last.usac.model.dto.StatusUpdateDTO;
import ma.youcode.usac_last.usac.model.entities.Child;
import ma.youcode.usac_last.usac.exception.ResourceNotFoundException;
import ma.youcode.usac_last.usac.model.enums.Status;
import ma.youcode.usac_last.usac.service.IChildService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @PostMapping()
    public ResponseEntity<ChildResponseDTO> saveChild(@RequestBody @Valid ChildCreateUpdateDTO childCreateUpdateDTO) {
        Child child =  childMapper.toEntity(childCreateUpdateDTO);
        Child savedChild = childService.saveChild(child);
        ChildResponseDTO childResponseDTO =  childMapper.toDTO(savedChild);
        return ResponseEntity.status(HttpStatus.CREATED).body(childResponseDTO);

    }


    @GetMapping
    public ResponseEntity<Page<ChildResponseDTO>> getAllChil(@RequestParam(defaultValue = "0") int page,
                                                               @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ChildResponseDTO> response = childService.getAllChildren(pageable)
                .map(childMapper::toDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{name}")
    public ResponseEntity<ChildResponseDTO> getChildByName(@PathVariable String name) {
        Child child = childService.getChildByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Enfant introuvable avec name : " + name));
        ChildResponseDTO childResponseDTO = childMapper.toDTO(child);
        return ResponseEntity.ok(childResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteChild(@PathVariable Long id) {
        childService.deleteChild(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ChildResponseDTO> updateChildStatus(@PathVariable Long id, @RequestBody StatusUpdateDTO statusUpdateDTO) {
        Child updatedChild = childService.updateChildStatus(id, statusUpdateDTO.getStatus());
        ChildResponseDTO responseDTO = childMapper.toDTO(updatedChild);
        return ResponseEntity.ok(responseDTO);
    }
    @GetMapping("/count/accepted")
    public ResponseEntity<Long> countAcceptedChildren() {
        long count = childService.countChildrenByStatus(Status.ACCEPTED);
        return ResponseEntity.ok(count);
    }



}
