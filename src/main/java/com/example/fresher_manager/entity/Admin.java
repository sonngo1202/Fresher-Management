package com.example.fresher_manager.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Admin")
@DiscriminatorValue("ADMIN")
@Data
public class Admin extends User{
}
