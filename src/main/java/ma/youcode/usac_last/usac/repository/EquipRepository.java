package ma.youcode.usac_last.usac.repository;

import ma.youcode.usac_last.usac.model.entities.Child;
import ma.youcode.usac_last.usac.model.entities.Equip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EquipRepository extends JpaRepository<Equip,Long> {
    Optional<Equip> findByName(String name);
}
