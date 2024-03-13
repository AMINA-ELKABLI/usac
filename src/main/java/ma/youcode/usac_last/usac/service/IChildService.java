package ma.youcode.usac_last.usac.service;

import ma.youcode.usac_last.usac.model.entities.Child;
import ma.youcode.usac_last.usac.model.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;



    public interface IChildService {
        Child saveChild(Child child);
        Page<Child> getAllChildren(Pageable pageable);
        Optional<Child>  getChildByName(String name);
        void deleteChild(Long id);
        long countChildrenByStatus(Status status);
        Child updateChildStatus(Long id, Status status);

    }


