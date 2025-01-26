package com.myclub.abc.service.serviceImpl;

import com.myclub.abc.model.ClassEntity;
import com.myclub.abc.repository.ClassRepository;
import com.myclub.abc.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    private final ClassRepository classRepository;

    public ClassServiceImpl(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    @Override
    public ClassEntity createClass(ClassEntity classEntity) {
        LocalDate currentDate = classEntity.getStartDate();
        while (!currentDate.isAfter(classEntity.getEndDate())) {
            classEntity.getDailyAvailability().put(currentDate, classEntity.getCapacity());
            currentDate = currentDate.plusDays(1);
        }
        return classRepository.save(classEntity);
    }

    @Override
    public Optional<ClassEntity> getClassById(Long id) {
        return classRepository.findById(id);
    }
}
