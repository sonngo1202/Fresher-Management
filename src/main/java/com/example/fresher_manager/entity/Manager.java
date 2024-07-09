package com.example.fresher_manager.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
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
