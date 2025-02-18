package com.school_management.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "tutor")
public class Tutor {
    @Id
    @Column(name="id")
    private int id;
    @Column(name="name")
    private String name;
    @Column(name="email")
    private String email;
    @Column(name="contact_number")
    private long contactNumber;
    @ManyToOne()
    @JoinColumn(name = "school_id")
    private School school;
}
