package com.school_management.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Table(name = "fee_payment")
@Entity
@Setter
@Getter
public class FeePayment {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "date")
    private Date date;
    @Column(name = "term")
    private String term;
    @Column(name="Amount")
    private double Amount;
    @Column(name = "status")
    private String status;
    @ManyToOne()
    @JoinColumn(name = "student_id")
    private Student student;


}
