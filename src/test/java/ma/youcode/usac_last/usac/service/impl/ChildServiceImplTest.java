package ma.youcode.usac_last.usac.service.impl;

import ma.youcode.usac_last.usac.exception.InvalidDataException;
import ma.youcode.usac_last.usac.exception.ResourceAlreadyExistsException;
import ma.youcode.usac_last.usac.exception.ResourceNotFoundException;
import ma.youcode.usac_last.usac.model.entities.Child;
import ma.youcode.usac_last.usac.model.entities.Equip;
import ma.youcode.usac_last.usac.model.enums.Status;
import ma.youcode.usac_last.usac.repository.ChildRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ChildServiceImplTest {
    @Mock
    private ChildRepository childRepository;
    @InjectMocks
    private ChildServiceImpl childService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        childService = new ChildServiceImpl(childRepository);
    }
   // save
   @Test
   public void testSaveChild_NewValidChild_ShouldSave() {

       Child newChild = new Child();
       newChild.setName("Test Child");
       newChild.setDateOfBirth(LocalDate.now().minusYears(10));
       when(childRepository.findByName(newChild.getName())).thenReturn(Optional.empty());
       when(childRepository.save(any(Child.class))).thenReturn(newChild);
       Child savedChild = childService.saveChild(newChild);
       assertNotNull(savedChild);
       assertEquals(newChild.getName(), savedChild.getName());
       verify(childRepository).save(any(Child.class));
   }
    @Test
    public void testSaveChild_ChildExists_ThrowsException() {
        // Arrange
        Child existingChild = new Child();
        existingChild.setId(1L);
        existingChild.setName("Test Child");
        existingChild.setDateOfBirth(LocalDate.now().minusYears(10));
        when(childRepository.findByName(existingChild.getName())).thenReturn(Optional.of(existingChild));
        assertThrows(ResourceAlreadyExistsException.class, () -> {
            childService.saveChild(existingChild);
        });
    }
    @Test
    public void test_saveChild_NullName_ThrowsInvalidDataException() {
        Child child = new Child();
        child.setDateOfBirth(LocalDate.now().minusYears(10));
        assertThrows(InvalidDataException.class, () -> {
            childService.saveChild(child);
        });
    }
    @Test
    public void test_saveChild_SameNamePendingChild_ShouldSave() {
        Child pendingChild = new Child();
        pendingChild.setId(1L);
        pendingChild.setName("Test Child");
        pendingChild.setDateOfBirth(LocalDate.now().minusYears(10));
        pendingChild.setStatus(Status.PENDING);
        when(childRepository.findByName(pendingChild.getName())).thenReturn(Optional.of(pendingChild));
        when(childRepository.save(any(Child.class))).thenReturn(pendingChild);
        Child savedChild = childService.saveChild(pendingChild);
        assertNotNull(savedChild);
        assertEquals(pendingChild.getName(), savedChild.getName());
        verify(childRepository).save(any(Child.class));
    }


    @Test
    public void testSaveChild_InvalidAge_ThrowsException() {
        Child child = new Child();
        child.setName("Test Child");
        child.setDateOfBirth(LocalDate.now().minusYears(5));
        assertThrows(InvalidDataException.class, () -> {
            childService.saveChild(child);
        });
    }



    // getAll
    @Test
    public void test_no_children_in_database() {
        ChildServiceImpl childService = new ChildServiceImpl(childRepository);
        Pageable pageable = PageRequest.of(0, 10);
        Page<Child> emptyPage = new PageImpl<>(new ArrayList<>());

        when(childRepository.findAll(pageable)).thenReturn(emptyPage);
        Page<Child> result = childService.getAllChildren(pageable);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
    //
    @Test
    public void test_null_name() {
        String name = null;
        Optional<Child> expected = Optional.empty();

        when(childRepository.findByName(name)).thenReturn(expected);

        Optional<Child> result = childService.getChildByName(name);

        assertEquals(expected, result);
    }
    @Test
    public void test_empty_name() {
        String name = "";
        Optional<Child> expected = Optional.empty();

        when(childRepository.findByName(name)).thenReturn(expected);

        Optional<Child> result = childService.getChildByName(name);

        assertEquals(expected, result);
    }
   // delete
   @Test
   public void test_deleteChild_validID() {
       Long id = 1L;
       Child child = new Child();
       child.setId(id);
       Optional<Child> optionalChild = Optional.of(child);
       when(childRepository.findById(id)).thenReturn(optionalChild);
       childService.deleteChild(id);
       verify(childRepository, times(1)).delete(child);
   }
    public void test_deleteChild_invalidID() {
        Long id = -1L;
        Optional<Child> optionalChild = Optional.empty();
        when(childRepository.findById(id)).thenReturn(optionalChild);
        assertThrows(ResourceNotFoundException.class, () -> {
            childService.deleteChild(id);
        });
    }

    //
    @Test
    public void test_countChildrenByStatus_withValidStatus() {
        ChildServiceImpl childService = new ChildServiceImpl(childRepository);
        Status status = Status.ACCEPTED;
        long count = childService.countChildrenByStatus(status);
        assertEquals(2, count);
    }
    @Test
    public void test_countChildrenByStatus_withNoChildrenWithStatus() {
        ChildServiceImpl childService = new ChildServiceImpl(childRepository);
        Status status = Status.REJECTED;
        long count = childService.countChildrenByStatus(status);
        assertEquals(0, count);
    }
}
