package ma.youcode.usac_last.usac.service.impl;

import lombok.AllArgsConstructor;
import ma.youcode.usac_last.usac.model.entities.Child;
import ma.youcode.usac_last.usac.exception.InvalidDataException;
import ma.youcode.usac_last.usac.exception.ResourceAlreadyExistsException;
import ma.youcode.usac_last.usac.exception.ResourceNotFoundException;
import ma.youcode.usac_last.usac.model.enums.Status;
import ma.youcode.usac_last.usac.repository.ChildRepository;
import ma.youcode.usac_last.usac.service.IChildService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
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
        if (age < 6 || age > 18) {
            throw new InvalidDataException("L'âge de l'enfant doit être compris entre 6 et 18 ans.");
        }
        return childRepository.save(child);
    }

    @Override
    public Page<Child> getAllChildren(Pageable pageable) {
        return childRepository.findAll(pageable);
    }


    @Override
    public Optional<Child> getChildByName(String name) {
        return childRepository.findByName(name);
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

    @Override
    public Long countChild() {
        return childRepository.count();
    }

    @Override
    public Child updateChildStatus(Long id, Status status) {
        Child child = childRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Child not found with ID: " + id));
        child.setStatus(status);
        return childRepository.save(child);
    }
}
