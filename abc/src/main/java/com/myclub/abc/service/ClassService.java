package com.myclub.abc.service;

import com.myclub.abc.model.ClassEntity;

import java.util.Optional;

public interface ClassService {

    public ClassEntity createClass(ClassEntity classEntity);

    public Optional<ClassEntity> getClassById(Long id);
}
