package com.example.fresher_manager.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Management")
@Data
public class Management extends Record{

    @ManyToOne
    @JoinColumn(name = "center_id")
    private Center center;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Manager manager;
}
