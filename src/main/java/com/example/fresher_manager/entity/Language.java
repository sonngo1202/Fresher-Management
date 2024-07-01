package com.example.fresher_manager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Language", uniqueConstraints = {@UniqueConstraint(columnNames = "name")})
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "des")
    private String des;

    public Language(String name, String des){
        this.name =  name;
        this.des = des;
    }
}
