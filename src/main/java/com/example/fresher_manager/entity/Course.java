package com.example.fresher_manager.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Course")
@Data
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Column(name = "end_date", nullable = false)
    private Date endDate;

    @Column(name = "des")
    private String des;

    @OneToMany(mappedBy = "course")
    private List<Enrollment> enrollments;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "center_id")
    private Center center;
}
