package com.school_management.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "student")

public class Student {
    @Id
    @Column(name="id")
    private int id;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Column(name="email")
    private String email;
    @Column(name="contact_number")
    private long contactNumber;
    @Column(name="enrollment_date")
    private String enrollmentDate;

    @ManyToOne()
    @JoinColumn(name = "school_id")
    private School school;
}
