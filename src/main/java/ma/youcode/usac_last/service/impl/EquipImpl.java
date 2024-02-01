package ma.youcode.usac_last.service.impl;

import ma.youcode.usac_last.usac.model.entities.Equip;
import ma.youcode.usac_last.usac.service.IEquip;

import java.util.List;
import java.util.Optional;

public class EquipImpl implements IEquip {
    @Override
    public Equip saveEquip(Equip equip) {
        return null;
    }

    @Override
    public List<Equip> getAllEquips() {
        return null;
    }

    @Override
    public Optional<Equip> getEquipByName(String name) {
        return Optional.empty();
    }

    @Override
    public void deleteEquip(Long id) {

    }
}
