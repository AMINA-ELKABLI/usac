package ma.youcode.usac_last.service.impl;

import lombok.AllArgsConstructor;
import ma.youcode.usac_last.entities.Child;
import ma.youcode.usac_last.exception.InvalidDataException;
import ma.youcode.usac_last.exception.ResourceAlreadyExistsException;
import ma.youcode.usac_last.exception.ResourceNotFoundException;
import ma.youcode.usac_last.repository.ChildRepository;
import ma.youcode.usac_last.service.IChildService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class ChildServiceImpl implements IChildService {
    private final ChildRepository childRepository;
    @Override
    public Child saveChild(Child child) {
      Optional<Child> existingChild = childRepository.findByName(child.getName());
      if(existingChild.isPresent() && !existingChild.get().getId().equals(child.getId())){
          throw  new ResourceAlreadyExistsException("Un enfant avec le même nom existe déjà.");
      }
        if (child.getName() == null || child.getDateOfBirth() == null) {
            throw new InvalidDataException("Le nom et la date de naissance sont requis.");
        }
        int age = Period.between(child.getDateOfBirth(), LocalDate.now()).getYears();
        if (age < 5 || age > 17) {
            throw new InvalidDataException("L'âge de l'enfant doit être compris entre 5 et 18 ans.");
        }
        return childRepository.save(child);
    }

    @Override
    public List<Child> getAllChildren() {
        return childRepository.findAll();
    }

    @Override
    public Optional<Child> getChildById(Long id) {
        return childRepository.findById(id);
    }

    @Override
    public void deleteChild(Long id) {
        Optional<Child> optionalChild = childRepository.findById(id);
        if (optionalChild.isPresent()) {
            Child child = optionalChild.get();

            childRepository.delete(child);
        } else {
            throw new ResourceNotFoundException("Enfant introuvable avec l'ID : " + id);
        }

    }
}
