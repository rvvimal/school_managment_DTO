package com.school_management.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name="course")
public class Course {
    @Id
    @Column(name="id")
    private int id;
    @Column(name="name")
    private String name;
}
