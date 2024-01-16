package ma.youcode.usac_last.service;

import ma.youcode.usac_last.entities.Child;

import java.util.List;
import java.util.Optional;



    public interface ChildService {
        Child saveChild(Child child);
        List<Child> getAllChildren();
      Optional<Child>  getChildById(Long id);
        void deleteChild(Long id);
    }


