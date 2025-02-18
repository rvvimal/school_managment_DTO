package com.school_management.repository;

import com.school_management.dto.SchoolDTO;
import com.school_management.entity.StudentCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentCourseRepository extends JpaRepository<StudentCourse, Integer> {


    List<StudentCourse> findByStudent_School_Id(Integer id);

    List<StudentCourse> findByStudent_School_Id(int schoolId);


}
