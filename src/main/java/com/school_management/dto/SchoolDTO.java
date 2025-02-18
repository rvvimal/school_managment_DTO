package com.school_management.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SchoolDTO {
    private Integer schoolId;
    private String schoolName;
//    private  int CourseId;''
    private int totalCourse;

}
