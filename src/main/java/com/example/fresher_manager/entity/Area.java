package com.example.fresher_manager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Area")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "des")
    private String des;

    @Column(name = "status", nullable = false)
    Boolean status;

    @PrePersist
    public void onCreate(){
        this.status = true;
    }

    public Area(String name, String des) {
        this.name = name;
        this.des = des;
        this.status = true;
    }
}
