package com.example.fresher_manager.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Record")
@Inheritance(strategy = InheritanceType.JOINED)
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Column(name = "end_date", nullable = false)
    private Date endDate;
}
