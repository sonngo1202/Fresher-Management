package com.example.fresher_manager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Fresher", uniqueConstraints = {@UniqueConstraint(columnNames = "code")})
@DiscriminatorValue("FRESHER")
@Data
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

    public float calculateAvgScore(){
        if(results.size() == 3){
            this.avg = 0;
            for(Result result : results) this.avg += result.getScore();
            return this.avg;
        }
        return -1;
    }
}
