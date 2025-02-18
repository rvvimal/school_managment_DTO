package com.school_management.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDetailsDTO {
    private int studentId;
    private String studentName;
    private String courseName;
}
