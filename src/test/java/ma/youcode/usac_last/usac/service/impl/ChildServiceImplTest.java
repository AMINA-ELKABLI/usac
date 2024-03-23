package ma.youcode.usac_last.usac.service.impl;
import ma.youcode.usac_last.usac.exception.InvalidDataException;
import ma.youcode.usac_last.usac.exception.ResourceNotFoundException;
import ma.youcode.usac_last.usac.model.entities.Child;
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
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
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

    @Test
    void saveChild_ShouldSave() {
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
    void testSaveChildShouldThrowWhenInvalid() {
        Child invalidChild = new Child();
        invalidChild.setName("Test Child");
        invalidChild.setDateOfBirth(LocalDate.now().minusYears(20)); // Age is out of valid range
        when(childRepository.findByName(anyString())).thenReturn(Optional.empty());
        assertThrows(InvalidDataException.class, () -> childService.saveChild(invalidChild));
    }

    @Test
    void testGetAllChildren() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Child> expectedPage = mock(Page.class);
        when(childRepository.findAll(pageable)).thenReturn(expectedPage);
        Page<Child> result = childService.getAllChildren(pageable);
        assertEquals(expectedPage, result);
    }
    @Test
    void testGetAllChildrenReturnsEmptyPageWhenNoChildrenFound() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Child> emptyPage = new PageImpl<>(Collections.emptyList());
        when(childRepository.findAll(pageable)).thenReturn(emptyPage);
        Page<Child> result = childService.getAllChildren(pageable);
        assertTrue(result.isEmpty());
    }



    @Test
    void getChildByNameShouldReturnChildWhenFound() {
        String name = "Test Name";
        Child expectedChild = new Child();
        expectedChild.setName(name);
        when(childRepository.findByName(name)).thenReturn(Optional.of(expectedChild));
        Optional<Child> result = childService.getChildByName(name);
        assertTrue(result.isPresent(), "Child should be found");
        assertEquals(expectedChild, result.get(), "The returned child should match the expected");
    }
    @Test
    void getChildByNameShouldReturnEmptyWhenNotFound() {
        String name = "Nonexistent Name";
        when(childRepository.findByName(name)).thenReturn(Optional.empty());
        Optional<Child> result = childService.getChildByName(name);
        assertFalse(result.isPresent(), "No child should be found for the given name");
    }


    @Test
    void deleteChildWhenChildExists() {

        Long id = 1L;
        Child existingChild = new Child();
        existingChild.setId(id);
        when(childRepository.findById(id)).thenReturn(Optional.of(existingChild));
        childService.deleteChild(id);
        verify(childRepository).delete(existingChild);
    }

    @Test
    void deleteChildWhenChildDoesNotExist() {
        Long id = 1L;
        when(childRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> childService.deleteChild(id));
    }
    @Test
    void countChildrenByStatus() {
        Status status = Status.ACCEPTED;
        long expectedCount = 10L;
        when(childRepository.countByStatus(status)).thenReturn(expectedCount);
        long result = childService.countChildrenByStatus(status);
        assertEquals(expectedCount, result);
    }


    @Test
    void getAcceptedChildren() {
        List<Child> expectedChildren = List.of(new Child());
        when(childRepository.findByStatus(Status.ACCEPTED)).thenReturn(expectedChildren);
        List<Child> result = childService.getAcceptedChildren();
        assertEquals(expectedChildren, result);
    }


    @Test
    void updateChildStatusWhenChildExists() {
        Long id = 1L;
        Status status = Status.ACCEPTED;
        Child existingChild = new Child();
        existingChild.setId(id);
        when(childRepository.findById(id)).thenReturn(Optional.of(existingChild));
        when(childRepository.save(any(Child.class))).thenReturn(existingChild);
        Child result = childService.updateChildStatus(id, status);
        assertEquals(status, result.getStatus());
        verify(childRepository).save(existingChild);
    }

    @Test
    void updateChildStatusWhenChildDoesNotExist() {
        Long id = 1L;
        Status status = Status.ACCEPTED;
        when(childRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> childService.updateChildStatus(id, status));
    }

}
