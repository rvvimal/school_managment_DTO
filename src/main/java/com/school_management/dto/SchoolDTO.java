package com.school_management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SchoolDTO {
    private Integer schoolId;
    private String schoolName;
    private int totalCourse;

}
