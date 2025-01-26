package com.myclub.abc.repository;

import com.myclub.abc.model.ClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClassRepository extends JpaRepository<ClassEntity,Long> {
    @Override
    Optional<ClassEntity> findById(Long aLong);
}
