package com.example.fresher_manager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Test")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "topic", nullable = false)
    private String topic;

    @Column(name = "des")
    private String des;

    @Column(name = "createdAt", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    //Call before saving for the first time
    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }

    @ManyToOne
    @JoinColumn(name = "rank_id", nullable = false)
    private Rank rank;
}
