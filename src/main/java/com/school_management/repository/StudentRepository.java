package com.school_management.repository;

import com.school_management.dto.StudentDetailsDTO;
import com.school_management.entity.FeePayment;
import com.school_management.entity.School;
import com.school_management.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Query(value="SELECT * FROM STUDENT "
//        +"WHERE (:name IS NULL OR name =:name)"
//        +"AND (:address IS NULL OR address = :address)"
//        +"AND (:schoolId IS NULL OR school_id = :schoolId)"
//        ,nativeQuery=true)
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{
//    List<Student> findByFirstName( String first_name);

    @Query("SELECT s FROM Student s " +
            "JOIN s.school sc " +
            "WHERE (:z IS NOT NULL AND :z != '' AND (sc.name LIKE CONCAT('%', :z, '%') " +
            "OR s.firstName LIKE CONCAT('%', :z, '%')))")
    List<Student> findByStudent_SchoolName(@Param("z") String z);

    @Query("SELECT s FROM Student s " +
            "JOIN s.school sc " +
            "WHERE sc.name LIKE CONCAT('%',:keyword, '%')OR" +
            " s.firstName LIKE CONCAT('%',:keyword, '%')")
    Page<Student> findByStudent_School(@Param("keyword") String keyword, Pageable pageable);
//    @Query(value = "SELECT s.id AS studentId, " +
//            "CONCAT(s.first_name, ' ', s.last_name) AS studentName, " +
//            "c.name AS courseName " +
//            "FROM student s " +
//            "JOIN student_course sc ON s.id = sc.student_id " +
//            "JOIN course c ON sc.course_id = c.id " +
//            "WHERE s.id = :id", nativeQuery = true)
//    List<StudentDetailsDTO>findStudent(int id);


}

