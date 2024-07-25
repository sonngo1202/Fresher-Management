package com.example.fresher_manager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.fresher_manager.entity.Manager;
import com.example.fresher_manager.exception.error.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class ManagerServiceTest {

    @Autowired
    private ManagerService managerService;

    @Test
    void getActiveUserByIdNotExists(){
        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class,
                () -> managerService.getActiveUserById(16L));
        assertEquals("Manager not found with id: 16", ex.getMessage());
    }

    @Test
    void getActiveUserByIdExists(){
        Manager manager = managerService.getActiveUserById(10L);
        assertEquals(10, manager.getId());
    }

    @Test
    void findAll(){
        List<Manager> managers = managerService.findAll();
        assertEquals(4, managers.size());
    }

    @Test
    void getCurrentManagerByCenterId(){
        Manager manager = managerService.getCurrentManagerByCenterId(7L);
        assertEquals(8L, manager.getId());
    }

    @Test
    void saveWithEmailExists(){
        Manager manager = new Manager();
        manager.setFirstname("Phu");
        manager.setLastname("Nguyen");
        manager.setEmail("maihuy@gmail.com");
        manager.setPhone("0896547002");
        manager.setDob(convertStringToSqlDate("2002-01-24"));
        manager.setUsername("nguyenphu");
        manager.setPassword("@Phu12345");

        EmailAlreadyExistsException ex = assertThrows(EmailAlreadyExistsException.class,
                () -> managerService.save(manager));

        assertEquals("Email is already taken!", ex.getMessage());
    }

    @Test
    void saveWithEmailNotFormat(){
        Manager manager = new Manager();
        manager.setFirstname("Phu");
        manager.setLastname("Nguyen");
        manager.setEmail("nguyenphu!.com");
        manager.setPhone("0896547002");
        manager.setDob(convertStringToSqlDate("2002-01-24"));
        manager.setUsername("nguyenphu");
        manager.setPassword("@Phu12345");

        ValidationException ex = assertThrows(ValidationException.class,
                () -> managerService.save(manager));

        assertEquals("Email is not valid.", ex.getMessage());
    }

    @Test
    void saveWithUsernameExists(){
        Manager manager = new Manager();
        manager.setFirstname("Phu");
        manager.setLastname("Nguyen");
        manager.setEmail("nguyenphu@gmail.com");
        manager.setPhone("0896547002");
        manager.setDob(convertStringToSqlDate("2002-01-24"));
        manager.setUsername("maihuy");
        manager.setPassword("@Phu12345");

        UsernameAlreadyExistsException ex = assertThrows(UsernameAlreadyExistsException.class,
                () -> managerService.save(manager));

        assertEquals("Username is already taken!", ex.getMessage());
    }

    @Test
    void saveWithUsernameIsNullOrEmpty(){
        Manager manager = new Manager();
        manager.setFirstname("Phu");
        manager.setLastname("Nguyen");
        manager.setEmail("nguyenphu@gmail.com");
        manager.setPhone("0896547002");
        manager.setDob(convertStringToSqlDate("2002-01-24"));
        manager.setUsername("");
        manager.setPassword("@Phu12345");

        ValidationException ex = assertThrows(ValidationException.class,
                () -> managerService.save(manager));

        assertEquals("Username cannot be null or empty", ex.getMessage());
    }

    @Test
    void saveWithPhoneExists(){
        Manager manager = new Manager();
        manager.setFirstname("Phu");
        manager.setLastname("Nguyen");
        manager.setEmail("nguyenphu@gmail.com");
        manager.setPhone("0789452134");
        manager.setDob(convertStringToSqlDate("2002-01-24"));
        manager.setUsername("nguyenphu");
        manager.setPassword("@Phu12345");

        PhoneAlreadyExistsException ex = assertThrows(PhoneAlreadyExistsException.class,
                () -> managerService.save(manager));

        assertEquals("Phone is already taken!", ex.getMessage());
    }

    @Test
    void saveWithPhoneNotFormat(){
        Manager manager = new Manager();
        manager.setFirstname("Phu");
        manager.setLastname("Nguyen");
        manager.setEmail("nguyenphu@gmail.com");
        manager.setPhone("089654700d");
        manager.setDob(convertStringToSqlDate("2002-01-24"));
        manager.setUsername("nguyenphu");
        manager.setPassword("@Phu12345");

        ValidationException ex = assertThrows(ValidationException.class,
                () -> managerService.save(manager));

        assertEquals("Phone number is not valid.", ex.getMessage());
    }

    @Test
    void saveWithSuitableData(){
        Manager manager = new Manager();
        manager.setFirstname("Phu");
        manager.setLastname("Nguyen");
        manager.setEmail("nguyenphu@gmail.com");
        manager.setPhone("0896547002");
        manager.setDob(convertStringToSqlDate("2002-01-24"));
        manager.setUsername("nguyenphu");
        manager.setPassword("@Phu12345");
        boolean rs = managerService.save(manager);

        assertEquals(true, rs);
    }

    private java.sql.Date convertStringToSqlDate(String dateString) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date utilDate = formatter.parse(dateString);
            return new java.sql.Date(utilDate.getTime());
        } catch (ParseException e) {
            return null;
        }
    }

}
