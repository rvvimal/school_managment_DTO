package com.school_management.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "course")
public class Course {
    @Id
    @Column(name = "id")
    private int id;
    @Size(min = 4, message = "Size should be greater than four")
    @Column(name = "name")
    private String name;
}
