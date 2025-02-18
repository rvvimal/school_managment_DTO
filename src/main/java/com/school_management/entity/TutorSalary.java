package com.school_management.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tutor_salary")
public class TutorSalary {
    @Id
    @Column(name="id")
    private int id;
    @Column(name="month")
    private String Month;
    @Column(name="paid")
    private String Paid;
    @Column(name="amount")
    private double Amount;
    @ManyToOne()
    @JoinColumn(name = "tutor_id")
    private Tutor tutor;
}
