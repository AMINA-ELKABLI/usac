package ma.youcode.usac_last.usac.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import ma.youcode.usac_last.usac.exception.InvalidDataException;
import ma.youcode.usac_last.usac.exception.ResourceNotFoundException;
import ma.youcode.usac_last.usac.model.entities.Child;
import ma.youcode.usac_last.usac.model.entities.Equip;
import ma.youcode.usac_last.usac.repository.ChildRepository;
import ma.youcode.usac_last.usac.repository.EquipRepository;
import ma.youcode.usac_last.usac.service.IEquipService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class EquipServiceImpl implements IEquipService {
    private final EquipRepository equipRepository;
    private final ChildRepository childRepository;
    @Override
    public Equip saveEquip(Equip equip) {
        if (equip.getName() == null || equip.getName().isEmpty()) {
            throw new InvalidDataException("Le nom de l'équipement ne peut pas être vide.");
        }

        if (equip.getDescription() == null || equip.getDescription().isEmpty()) {
            throw new InvalidDataException("La description de l'équipement ne peut pas être vide.");
        }

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
    public Equip assignChildToEquip(Long childId, Long equipId) {
        Child child = childRepository.findById(childId)
                .orElseThrow(() -> new EntityNotFoundException("Child not found with id " + childId));
        Equip equip = equipRepository.findById(equipId)
                .orElseThrow(() -> new EntityNotFoundException("Equip not found with id " + equipId));

        equip.getChildren().add(child);
        child.getEquips().add(equip);

        equipRepository.save(equip);
        childRepository.save(child);

        return equip;
    }
}
