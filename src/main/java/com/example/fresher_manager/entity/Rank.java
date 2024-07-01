package com.example.fresher_manager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "`Rank`", uniqueConstraints = {@UniqueConstraint(columnNames = "name")})
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Rank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "des")
    private String des;

    public Rank(String name, String des){
        this.name = name;
        this.des = des;
    }
}
