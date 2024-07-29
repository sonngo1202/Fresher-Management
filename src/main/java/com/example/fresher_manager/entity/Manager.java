package com.example.fresher_manager.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "Manager")
@Data
public class Manager extends User{

    @OneToMany(mappedBy = "manager")
    private List<Management> managements;
}
