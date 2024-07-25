package com.example.fresher_manager.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.fresher_manager.entity.Fresher;
import com.example.fresher_manager.entity.Language;
import com.example.fresher_manager.exception.error.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
public class FresherServiceTest {

    @Autowired
    private FresherService fresherService;

    @Test
    void saveWithUsernameIsNullOrEmpty(){
        Fresher fresher = new Fresher();
        fresher.setFirstname("Long");
        fresher.setLastname("Nguyen");
        fresher.setEmail("nguyenlong@gmail.com");
        fresher.setPhone("0748923134");
        fresher.setDob(convertStringToSqlDate("2002-10-10"));
        fresher.setUsername("");
        fresher.setPassword("@Long12345");
        fresher.setCode("NL005");
        Language language = new Language();
        language.setId(1L);
        fresher.setLanguage(language);

        ValidationException ex = assertThrows(ValidationException.class,
                () -> fresherService.save(fresher));

        assertEquals("Username cannot be null or empty", ex.getMessage());
    }

    @Test
    void saveWithUsernameExists(){
        Fresher fresher = new Fresher();
        fresher.setFirstname("Long");
        fresher.setLastname("Nguyen");
        fresher.setEmail("nguyenlong@gmail.com");
        fresher.setPhone("0748923134");
        fresher.setDob(convertStringToSqlDate("2002-10-10"));
        fresher.setUsername("trantham");
        fresher.setPassword("@Long12345");
        fresher.setCode("NL005");
        Language language = new Language();
        language.setId(1L);
        fresher.setLanguage(language);

        UsernameAlreadyExistsException ex = assertThrows(UsernameAlreadyExistsException.class,
                () -> fresherService.save(fresher));

        assertEquals("Username is already taken!", ex.getMessage());
    }

    @Test
    void saveWithEmailExists(){
        Fresher fresher = new Fresher();
        fresher.setFirstname("Long");
        fresher.setLastname("Nguyen");
        fresher.setEmail("thamtran@gmail.com");
        fresher.setPhone("0748923134");
        fresher.setDob(convertStringToSqlDate("2002-10-10"));
        fresher.setUsername("nguyenlong");
        fresher.setPassword("@Long12345");
        fresher.setCode("NL005");
        Language language = new Language();
        language.setId(1L);
        fresher.setLanguage(language);

        EmailAlreadyExistsException ex = assertThrows(EmailAlreadyExistsException.class,
                () -> fresherService.save(fresher));

        assertEquals("Email is already taken!", ex.getMessage());
    }

    @Test
    void saveWithEmailNotFormat(){
        Fresher fresher = new Fresher();
        fresher.setFirstname("Long");
        fresher.setLastname("Nguyen");
        fresher.setEmail("");
        fresher.setPhone("0748923134");
        fresher.setDob(convertStringToSqlDate("2002-10-10"));
        fresher.setUsername("nguyenlong");
        fresher.setPassword("@Long12345");
        fresher.setCode("NL005");
        Language language = new Language();
        language.setId(1L);
        fresher.setLanguage(language);

        ValidationException ex = assertThrows(ValidationException.class,
                () -> fresherService.save(fresher));

        assertEquals("Email is not valid.", ex.getMessage());
    }

    @Test
    void saveWithPhoneNotFormat(){
        Fresher fresher = new Fresher();
        fresher.setFirstname("Long");
        fresher.setLastname("Nguyen");
        fresher.setEmail("longnguyen@gmail.com");
        fresher.setPhone("");
        fresher.setDob(convertStringToSqlDate("2002-10-10"));
        fresher.setUsername("nguyenlong");
        fresher.setPassword("@Long12345");
        fresher.setCode("NL005");
        Language language = new Language();
        language.setId(1L);
        fresher.setLanguage(language);

        ValidationException ex = assertThrows(ValidationException.class,
                () -> fresherService.save(fresher));

        assertEquals("Phone number is not valid.", ex.getMessage());
    }

    @Test
    void saveWithPhoneExists(){
        Fresher fresher = new Fresher();
        fresher.setFirstname("Long");
        fresher.setLastname("Nguyen");
        fresher.setEmail("longnguyen@gmail.com");
        fresher.setPhone("0654450987");
        fresher.setDob(convertStringToSqlDate("2002-10-10"));
        fresher.setUsername("nguyenlong");
        fresher.setPassword("@Long12345");
        fresher.setCode("NL005");
        Language language = new Language();
        language.setId(1L);
        fresher.setLanguage(language);

        PhoneAlreadyExistsException ex = assertThrows(PhoneAlreadyExistsException.class,
                () -> fresherService.save(fresher));

        assertEquals("Phone is already taken!", ex.getMessage());
    }

    @Test
    void saveWithCodeExists(){

    }

    @Test
    void saveWithCodeIsNullOrEmpty(){
        Fresher fresher = new Fresher();
        fresher.setFirstname("Long");
        fresher.setLastname("Nguyen");
        fresher.setEmail("nguyenlong@gmail.com");
        fresher.setPhone("0748923134");
        fresher.setDob(convertStringToSqlDate("2002-10-10"));
        fresher.setUsername("nguyenlong");
        fresher.setPassword("@Long12345");
        fresher.setCode("");
        Language language = new Language();
        language.setId(1L);
        fresher.setLanguage(language);

        ValidationException ex = assertThrows(ValidationException.class,
                () -> fresherService.save(fresher));

        assertEquals("Fresher Code cannot be null or empty", ex.getMessage());
    }

    @Test
    void saveWithLanguageNotExists(){
        Fresher fresher = new Fresher();
        fresher.setFirstname("Long");
        fresher.setLastname("Nguyen");
        fresher.setEmail("nguyenlong@gmail.com");
        fresher.setPhone("0748923134");
        fresher.setDob(convertStringToSqlDate("2002-10-10"));
        fresher.setUsername("nguyenlong");
        fresher.setPassword("@Long12345");
        fresher.setCode("NL005");
        Language language = new Language();
        language.setId(20L);
        fresher.setLanguage(language);

        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class,
                () -> fresherService.save(fresher));

        assertEquals("Language not found with id: 20", ex.getMessage());
    }

    @Test
    void saveWithSuitableData(){
        Fresher fresher = new Fresher();
        fresher.setFirstname("Long");
        fresher.setLastname("Nguyen");
        fresher.setEmail("nguyenlong@gmail.com");
        fresher.setPhone("0748923134");
        fresher.setDob(convertStringToSqlDate("2002-10-10"));
        fresher.setUsername("nguyenlong");
        fresher.setPassword("@Long12345");
        fresher.setCode("NL005");
        Language language = new Language();
        language.setId(1L);
        fresher.setLanguage(language);

        assertEquals(true, fresherService.save(fresher));
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
