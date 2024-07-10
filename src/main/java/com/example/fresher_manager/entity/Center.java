package com.example.fresher_manager.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Center", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name"),
        @UniqueConstraint(columnNames = "phone"),
        @UniqueConstraint(columnNames = "email")})
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Center {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "status", nullable = false)
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "area_id", nullable = false)
    private Area area;

    @OneToMany(mappedBy = "newCenter")
    private List<History> histories;

    @JsonBackReference
    @OneToMany(mappedBy = "center")
    private List<Management> managements;

    @OneToMany(mappedBy = "center")
    private List<Course> courses;

    public Center(Long id, String name, String address){
        this.id = id;
        this.name = name;
        this.address = address;
    }

    @PrePersist
    protected void onCreate(){
        this.status = true;
    }
}
