package ma.youcode.usac_last.usac.service;

import ma.youcode.usac_last.usac.model.entities.Equip;

import java.util.List;
import java.util.Optional;

public interface IEquip {
    Equip saveEquip(Equip equip);
    List<Equip> getAllEquips();
    Optional<Equip> getEquipByName(String name);
    void deleteEquip(Long id);
    Equip updateEquip(Equip equip);
}