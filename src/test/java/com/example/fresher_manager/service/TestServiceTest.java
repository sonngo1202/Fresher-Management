package com.example.fresher_manager.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.fresher_manager.exception.error.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestServiceTest {

    @Autowired
    private TestService testService;

    @Test
    void findByIdExists(){
        com.example.fresher_manager.entity.Test test = testService.findById(1L);
        assertEquals(1L, test.getId());
    }

    @Test
    void findByIdNotExists(){
        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class,
                () -> testService.findById(10L));
        assertEquals("Test not found with id: 10", ex.getMessage());
    }

}
