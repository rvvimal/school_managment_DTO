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
@Table(name = "school")
public class School {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    @Size(min = 4, message = "Size should be greater than four")
    private String name;
    @Column(name = "address")
    @Size(min = 4, message = "Size should be greater than four")
    private String address;
    @Column(name = "contact")
    private long contact;

}
