package com.example.fresher_manager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Fresher")
@DiscriminatorValue("FRESHER")
public class Fresher extends User{
    @Transient
    private float avg;

    @Column(name = "code", nullable = false)
    private String code;

    @ManyToOne
    @JoinColumn(name = "language_id", nullable = false)
    private Language language;

    @OneToMany(mappedBy = "fresher")
    private List<Result> results;
}
