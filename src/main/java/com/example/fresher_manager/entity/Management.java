package com.example.fresher_manager.entity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "Management")
public class Management extends Record{

    @OneToMany(mappedBy = "management")
    private List<Center> centers;
}
