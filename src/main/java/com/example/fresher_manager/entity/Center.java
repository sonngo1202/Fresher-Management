package com.example.fresher_manager.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Center", uniqueConstraints = {@UniqueConstraint(columnNames = "name")})
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

    @JsonIgnoreProperties("centers")
    @ManyToOne
    @JoinColumn(name = "management_id")
    private Management management;

    @OneToMany(mappedBy = "center")
    private List<Course> courses;

    protected void onCreate(){
        this.status = true;
    }
}
