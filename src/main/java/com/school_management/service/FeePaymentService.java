package com.school_management.service;

import com.school_management.dto.ResponseDTO;
import com.school_management.dto.SchoolDTO;
import com.school_management.dto.SchoolFeeDetailsDTO;
import com.school_management.entity.FeePayment;
import com.school_management.entity.School;
import com.school_management.entity.Student;
import com.school_management.entity.StudentCourse;
import com.school_management.exception.UserNotFoundException;
import com.school_management.repository.FeePaymentRepository;
import com.school_management.util.Constant;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

@Service
public class FeePaymentService {
    @Autowired
    private FeePaymentRepository feePaymentRepository;
    @Transactional
    public ResponseDTO createFeePayment(final FeePayment feePayment) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.CREATE, this.feePaymentRepository.save(feePayment));
    }

    public ResponseDTO getAllFeePayment() {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE, this.feePaymentRepository.findAll());
    }

    public ResponseDTO findById(final int id) {
        if (!this.feePaymentRepository.existsById(id)) {
            throw new UserNotFoundException(Constant.ID_DOES_NOT_EXIST);
        }
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE, this.feePaymentRepository.findById(id));
    }

    public ResponseDTO deleteById(final int id) {
        if (this.feePaymentRepository.existsById(id)) {
            this.feePaymentRepository.deleteById(id);
        } else {
            throw new UserNotFoundException(Constant.FOUND);
        }
        return new ResponseDTO(HttpStatus.OK.value(), Constant.DELETE, Constant.REMOVE);
    }
    @Transactional
    public ResponseDTO updateById(final FeePayment feePayment,final int id) {
        final FeePayment feePaymentObject = this.feePaymentRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(Constant.ID_DOES_NOT_EXIST));

        if (feePayment.getDate() != null) {
            feePaymentObject.setDate(feePayment.getDate());
        }
        if (feePayment.getAmount() != 0) {
            feePaymentObject.setAmount(feePayment.getAmount());
        }
        if (feePayment.getStatus() != null) {
            feePaymentObject.setStatus(feePayment.getStatus());
        }
        if (feePayment.getTerm() != null) {
            feePaymentObject.setTerm(feePayment.getTerm());
        }

        return new ResponseDTO(HttpStatus.OK.value(), Constant.UPDATE, this.feePaymentRepository.save(feePaymentObject));
    }

    public List<SchoolFeeDetailsDTO> saler(int id) {
        List<FeePayment> students = this.feePaymentRepository.findByStudent_School_Id(id);
        List<SchoolFeeDetailsDTO> schoolDetailsDTOS = new ArrayList<>();

        if (!students.isEmpty()) {
            SchoolFeeDetailsDTO schoolDTO = new SchoolFeeDetailsDTO();
            schoolDTO.setSchoolId(students.get(0).getStudent().getSchool().getId());
            schoolDTO.setSchoolName(students.get(0).getStudent().getSchool().getName());

            double minFee = Double.MAX_VALUE;
            double maxFee = Double.MIN_VALUE;

            for (FeePayment payment : students) {
                double amount = payment.getAmount();
                if (amount < minFee) {
                    minFee = amount;
                }
                if (amount > maxFee) {
                    maxFee = amount;
                }
            }
            schoolDTO.setMinimumFee(minFee);
            schoolDTO.setMaximumFee(maxFee);

            schoolDetailsDTOS.add(schoolDTO);
        }

        return schoolDetailsDTOS;
    }

}