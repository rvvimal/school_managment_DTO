package com.school_management.repository;

import com.school_management.entity.FeePayment;
import com.school_management.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeePaymentRepository extends JpaRepository<FeePayment, Integer> {

    List<FeePayment> findByStudent_School_Id(int id);
}
