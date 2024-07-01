package com.example.fresher_manager.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Enrollment")
public class Enrollment extends Record{

    @ManyToOne
    @JoinColumn(name = "fresher_id")
    private Fresher fresher;

    @JsonIgnoreProperties("enrollments")
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}
