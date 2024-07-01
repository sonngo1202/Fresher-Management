package com.example.fresher_manager.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "History")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "des")
    private String des;

    @JsonIgnoreProperties("histories")
    @ManyToOne
    @JoinColumn(name = "center1_id", nullable = false)
    private Center oldCenter1;

    @JsonIgnoreProperties("histories")
    @ManyToOne
    @JoinColumn(name = "center2_id", nullable = false)
    private Center oldCenter2;

    @JsonIgnoreProperties("histories")
    @ManyToOne
    @JoinColumn(name = "new_center_id", nullable = false)
    private Center newCenter;
}
