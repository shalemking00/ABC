package com.myclub.abc.service;

import com.myclub.abc.model.ClassEntity;
import com.myclub.abc.repository.ClassRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ClassServiceTest {
    @Mock
    private ClassRepository classRepository;

    @InjectMocks
    private ClassService classService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateClass() {
        ClassEntity classEntity = new ClassEntity();
        classEntity.setName("Yoga");
        classEntity.setStartDate(LocalDate.of(2025, 2, 1));
        classEntity.setEndDate(LocalDate.of(2025, 2, 10));
        classEntity.setStartTime(LocalTime.of(10, 0));
        classEntity.setDuration(60);
        classEntity.setCapacity(10);

        when(classRepository.save(classEntity)).thenReturn(classEntity);

        ClassEntity createdClass = classService.createClass(classEntity);

        assertNotNull(createdClass);
        assertEquals("Yoga", createdClass.getName());
        verify(classRepository, times(1)).save(classEntity);
    }

    @Test
    void testGetClassById() {
        ClassEntity classEntity = new ClassEntity();
        classEntity.setId(1L);
        classEntity.setName("Yoga");

        when(classRepository.findById(1L)).thenReturn(Optional.of(classEntity));

        Optional<ClassEntity> foundClass = classService.getClassById(1L);

        assertTrue(foundClass.isPresent());
        assertEquals("Yoga", foundClass.get().getName());
        verify(classRepository, times(1)).findById(1L);
    }
}
