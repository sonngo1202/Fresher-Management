package com.example.fresher_manager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateFresherRequest {
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private Date dob;
    private Long languageId;
}
