package com.example.fresher_manager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CenterRequest {
    private String name;
    private String address;
    private String phone;
    private String email;
    private Long managerId;
    private Long areaId;
}
