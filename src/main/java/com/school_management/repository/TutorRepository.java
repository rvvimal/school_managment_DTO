package com.school_management.repository;

import com.school_management.entity.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorRepository extends JpaRepository<Tutor, Integer> {
//@Query(value = "SELECT t.id AS tutor_id, t.name AS tutor_name, ts.amount, s.id AS schoolId " +
//        "FROM tutor t " +
//        "JOIN tutor_salary ts ON t.id = ts.tutor_id " +
//        "JOIN school s ON t.school_id = s.id " +
//        "WHERE t.school_id = :schoolId " +
//        "AND (:Amount IS NULL OR ts.amount > :Amount)",
//        nativeQuery = true)
//List<TutorSalaryDTO> findTutorBySalary(@Param("schoolId") int schoolId,
//                                       @Param("Amount") Double Amount);
}
