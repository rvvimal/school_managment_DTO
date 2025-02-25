package com.school_management.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tutor_salary")
public class TutorSalary {
    @Id
    @Column(name = "id")
    private int id;
    @Size(min = 4, message = "Size should be greater than four")
    @Column(name = "month")
    private String Month;
    @Size(min = 4, message = "Size should be greater than four")
    @Column(name = "paid")
    private String Paid;
    @Column(name = "amount")
    private double Amount;
    @ManyToOne()
    @JoinColumn(name = "tutor_id")
    private Tutor tutor;
}
