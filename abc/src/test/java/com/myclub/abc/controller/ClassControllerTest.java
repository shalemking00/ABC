package com.myclub.abc.controller;

import com.myclub.abc.repository.ClassRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class ClassControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClassRepository classRepository;

    @Test
    void testCreateClass() throws Exception {
        String json = """
                {
                    "name": "Yoga",
                    "startDate": "2025-02-01",
                    "endDate": "2025-02-10",
                    "startTime": "10:00",
                    "duration": 60,
                    "capacity": 10
                }
                """;

        mockMvc.perform(post("/api/classes")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isCreated());

        // Verify the class was added
        assertFalse(classRepository.findAll().isEmpty());
    }
}
