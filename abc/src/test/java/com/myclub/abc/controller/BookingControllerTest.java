package com.myclub.abc.controller;

import com.myclub.abc.repository.BookingRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class BookingControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookingRepository bookingRepository;

    @Test
    void testCreateBooking() throws Exception {
        String json = """
                {
                    "memberName": "Alice",
                    "classEntity": { "id": 1 },
                    "participationDate": "2025-02-05"
                }
                """;

//        mockMvc.perform(post("/api/bookings")
//                        .contentType(MediaType.valueOf("application/json"))
//                        .content(json))
//                .andExpect(status().isOk());
        when(bookingRepository.findByMemberName("Alice")).thenReturn(List.of(booking));

        // Verify the booking was added
        assertFalse(bookingRepository.findAll().isEmpty());
    }
}
