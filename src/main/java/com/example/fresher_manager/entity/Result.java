package com.example.fresher_manager.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Result")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "testDateTime", nullable = false)
    private LocalDateTime testDateTime;

    @Column(name = "score", nullable = false)
    private float score;

    @Column(name = "comment")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "test_id", nullable = false)
    private Test test;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "fresher_id", nullable = false)
    private Fresher fresher;
}
