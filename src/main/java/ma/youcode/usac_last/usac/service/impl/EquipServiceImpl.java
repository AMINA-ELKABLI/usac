package ma.youcode.usac_last.usac.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import ma.youcode.usac_last.usac.exception.InvalidDataException;
import ma.youcode.usac_last.usac.exception.ResourceAlreadyExistsException;
import ma.youcode.usac_last.usac.exception.ResourceNotFoundException;
import ma.youcode.usac_last.usac.model.dto.EquipCreateUpdateDTO;
import ma.youcode.usac_last.usac.model.entities.Child;
import ma.youcode.usac_last.usac.model.entities.Equip;
import ma.youcode.usac_last.usac.model.enums.Status;
import ma.youcode.usac_last.usac.repository.ChildRepository;
import ma.youcode.usac_last.usac.repository.EquipRepository;
import ma.youcode.usac_last.usac.repository.MatchRepository;
import ma.youcode.usac_last.usac.service.IEquipService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EquipServiceImpl implements IEquipService {
    private final EquipRepository equipRepository;
    private final ChildRepository childRepository;
    private final MatchRepository matchRepository;
    @Override
    public Equip saveEquip(EquipCreateUpdateDTO equipDTO) {
        if (equipRepository.existsByName(equipDTO.getName())) {
            throw new ResourceAlreadyExistsException("Une équipe avec le même nom existe déjà.");
        }

        Set<Child> children = new HashSet<>();
        if (equipDTO.getChildIds() != null && !equipDTO.getChildIds().isEmpty()) {
            List<Long> childIds = equipDTO.getChildIds();
             List<Child> foundChildren = childRepository.findAllById(childIds);
             children = foundChildren.stream().filter(child -> child.getStatus() == Status.ACCEPTED)
                     .collect(Collectors.toSet());
            if (children.size() != childIds.size()) {
                throw new InvalidDataException("Un ou plusieurs enfants spécifiés n'existent pas.");
            }
        }

        Equip equip = new Equip();
        equip.setName(equipDTO.getName());
        equip.setDescription(equipDTO.getDescription());
        equip.setChildren(children);
        return equipRepository.save(equip);
    }
    @Override
    public List<Equip> getAllEquips() {
        return equipRepository.findAll();
    }
    @Override
    public Optional<Equip> getEquipByName(String name) {
        Optional<Equip> equipOptional = equipRepository.findByName(name);
        if (equipOptional.isEmpty()) {
            throw new IllegalArgumentException("Aucun équipement trouvé avec le nom : " + name);
        }
        return equipOptional;
    }

    @Override
    public void deleteEquip(Long id) {
        if (equipRepository.existsById(id)) {
            equipRepository.deleteById(id);
        } else {
            throw new InvalidDataException("Aucun équipement trouvé avec l'ID : " + id);
        }

    }

    @Override
    public Equip updateEquip(Equip equip) {
        String equipName = equip.getName();
        Equip existingEquip = equipRepository.findByName(equipName)
                .orElseThrow(() -> new ResourceNotFoundException("Équipement introuvable avec l'ID : " + equipName));
        existingEquip.setName(equip.getName());
        existingEquip.setDescription(equip.getDescription());
        return equipRepository.save(existingEquip);
    }

    @Override
    public long getTotalEquips() {

        return equipRepository.count();
    }

    @Override
    public List<Child> findChildrenByEquipId(Long equipId) {
        Equip equip = equipRepository.findById(equipId)
                .orElseThrow(() -> new ResourceNotFoundException("Equip not found with id: " + equipId));
        return new ArrayList<>(equip.getChildren());
    }


}
