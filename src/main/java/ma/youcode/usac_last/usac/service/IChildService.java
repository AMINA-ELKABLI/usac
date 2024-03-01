package ma.youcode.usac_last.usac.service;

import ma.youcode.usac_last.usac.model.entities.Child;

import java.util.List;
import java.util.Optional;



    public interface IChildService {
        Child saveChild(Child child);
        List<Child> getAllChildren();
      Optional<Child>  getChildByName(String name);
        void deleteChild(Long id);
    }


