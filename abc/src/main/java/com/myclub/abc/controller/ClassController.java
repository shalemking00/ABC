package com.myclub.abc.controller;

import com.myclub.abc.model.ClassEntity;
import com.myclub.abc.service.ClassService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/classes")
public class ClassController {
    private final ClassService classService;

    public ClassController(ClassService classService) {
        this.classService = classService;
    }

    @PostMapping
    public ResponseEntity<ClassEntity> createClass(@RequestBody ClassEntity classEntity) {
        if (classEntity.getCapacity() < 1) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.status(201).body(classService.createClass(classEntity));
    }
}
