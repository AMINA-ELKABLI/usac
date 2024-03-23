package ma.youcode.usac_last.usac.service;

import ma.youcode.usac_last.usac.model.dto.EquipCreateUpdateDTO;
import ma.youcode.usac_last.usac.model.entities.Child;
import ma.youcode.usac_last.usac.model.entities.Equip;

import java.util.List;
import java.util.Optional;

public interface IEquipService {
    Equip saveEquip(EquipCreateUpdateDTO equipDTO);
    List<Equip> getAllEquips();
    Optional<Equip> getEquipByName(String name);
    void deleteEquip(Long id);
    Equip updateEquip(Equip equip);
    long getTotalEquips();
    List<Child> findChildrenByEquipId(Long equipId);



}
