package com.school_management.repository;

import com.school_management.dto.SchoolDTO;

import com.school_management.dto.SchoolDetailsDTO;
import com.school_management.dto.SchoolFeeDetailsDTO;
import com.school_management.entity.School;
import com.school_management.entity.StudentCourse;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Spliterator;


@Repository
public interface SchoolRepository extends JpaRepository<School, Integer> {
//    @Query(value =
//            "SELECT school.id AS id , school.name AS schoolName, " +
//            "tutor.id AS tutorId, tutor.name AS tutorName, " +
//            "student.id AS studentId, student.first_name AS studentFirstName, student.last_name AS studentLastName, " +
//            "student.enrollment_date AS enrollmentDate, " +
//            "course.id AS courseId, course.name AS courseName " +
//            "FROM school " +
//            "JOIN tutor ON school.id = tutor.school_id " +
//            "JOIN student ON school.id = student.school_id " +
//            "JOIN student_course ON student.id = student_course.student_id " +
//            "JOIN course ON student_course.course_id = course.id " +
//            "WHERE school.id = :id", nativeQuery = true)
//@Query("SELECT s FROM Student s WHERE s.tutor.school.id = :schoolId")
//List<Student> findStudentsByTutorSchoolId(@Param("schoolId") int schoolId);

//    List<School> findStudentByTutorSchoolId(int id);


//@Query(value = "SELECT school.id AS schoolId, school.name AS schoolName, " +
//        "COUNT(student_course.course_id) AS totalCourse " +
//        "FROM school  " +
//        "JOIN student ON school.id = student.school_id " +
//        "JOIN student_course  ON student.id = student_course.student_id " +
//        "GROUP BY school.id, school.name", nativeQuery = true)
//List<School>findCountSchool();
//
//    @Query(value = "SELECT s.id AS school_id, s.name AS school_name, " +
//            "MIN(fp.amount) AS minimum_fee, " +
//            "MAX(fp.amount) AS maximum_fee " +
//            "FROM school s " +
//            "JOIN student ON s.id = student.school_id " +
//            "JOIN fee_payment fp ON student.id = fp.student_id " +
//            "GROUP BY s.id, s.name",
//            nativeQuery = true)
//
//    List<SchoolFeeDetailsDTO> findSchoolFeeDetails();

}
