package com.school_management.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "tutor_course")
public class TutorCourse {
    @Id
    @Column(name = "id")
    private int id;
    @ManyToOne()
    @JoinColumn(name = "course_id")
    private Course course;
    @ManyToOne()
    @JoinColumn(name = "tutor_id")
    private Tutor tutor;
}
