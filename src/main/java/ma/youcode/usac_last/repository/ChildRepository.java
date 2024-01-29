package ma.youcode.usac_last.repository;

import ma.youcode.usac_last.entities.Child;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChildRepository extends JpaRepository<Child, Long>  {

    Optional<Child> findByName(String name);
}
