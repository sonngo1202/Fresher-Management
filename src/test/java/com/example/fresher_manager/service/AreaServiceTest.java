package com.example.fresher_manager.service;

import com.example.fresher_manager.entity.Area;
import com.example.fresher_manager.exception.error.ResourceNotFoundException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class AreaServiceTest {

    @Autowired
    private AreaService areaService;

    @Test
    void testGetActiveByIdNotExists() {
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () ->
                areaService.getActiveById(5L));
        assertEquals("Area not found with id: 5", exception.getMessage());
    }

    @Test
    void testGetActiveByIdExists(){
        Area area = areaService.getActiveById(1L);
        assertEquals(1L, area.getId());
    }
}
