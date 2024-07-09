package com.example.fresher_manager.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Fresher", uniqueConstraints = {@UniqueConstraint(columnNames = "code")})
@Data
public class Fresher extends User{

    private static final int MAX_TESTS = 3;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Transient
    private Float avg;

    @Column(name = "code", nullable = false)
    private String code;

    @ManyToOne
    @JoinColumn(name = "language_id", nullable = false)
    private Language language;

    @OneToMany(mappedBy = "fresher")
    private List<Result> results;

    @JsonBackReference(value = "fresher-enrollments")
    @OneToMany(mappedBy = "fresher")
    private List<Enrollment> enrollments;

    @JsonProperty("avg")
    public Float getAvg(){
        if(results != null && results.size() == MAX_TESTS){
            return calculateAvgScore();
        }
        return null;
    }

    private Float calculateAvgScore() {
        float totalScore = 0;
        for (Result result : results) {
            totalScore += result.getScore();
        }
        return totalScore / results.size();
    }
}
