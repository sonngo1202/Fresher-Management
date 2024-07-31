package com.example.fresher_manager.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Enrollment")
@Data
public class Enrollment extends Record{

    @ManyToOne
    @JoinColumn(name = "fresher_id")
    private Fresher fresher;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}
