package com.school_management.service;


import com.school_management.dto.ResponseDTO;
import com.school_management.dto.TutorSalaryDTO;
import com.school_management.entity.TutorSalary;
import com.school_management.exception.UserNotFoundException;
import com.school_management.repository.TutorSalaryRepository;
import com.school_management.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TutorSalaryService {
    @Autowired
    private TutorSalaryRepository tutorSalaryRepository;

    @Transactional
    public ResponseDTO createTutorSalary(final TutorSalary tutorSalary) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.CREATE, this.tutorSalaryRepository.save(tutorSalary));
    }

    public ResponseDTO getAlltutorSalary() {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE, this.tutorSalaryRepository.findAll());
    }

    public ResponseDTO findById(final int id) {
        if (!this.tutorSalaryRepository.existsById(id)) {
            throw new UserNotFoundException(Constant.ID_DOES_NOT_EXIST);
        }
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE,
                this.tutorSalaryRepository.findById(id));
    }


    public ResponseDTO deleteById(final int id) {
        if (this.tutorSalaryRepository.existsById(id)) {
            this.tutorSalaryRepository.deleteById(id);
        }
        return new ResponseDTO(HttpStatus.OK.value(), Constant.DELETE,
                Constant.REMOVE);
    }

    @Transactional
    public ResponseDTO updateById(final TutorSalary tutorSalary, final int id) {
        final TutorSalary tutorSalaryObject = this.tutorSalaryRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(Constant.ID_DOES_NOT_EXIST));

        if (tutorSalary.getMonth() != null) {
            tutorSalaryObject.setMonth(tutorSalary.getMonth());
        }

        if (tutorSalary.getAmount() != 0) {
            tutorSalaryObject.setAmount(tutorSalary.getAmount());
        }
        if (tutorSalary.getPaid() != null) {
            tutorSalaryObject.setPaid(tutorSalary.getPaid());
        }
        return new ResponseDTO(HttpStatus.OK.value(), Constant.UPDATE, this.tutorSalaryRepository.save(tutorSalaryObject));
    }


    public List<TutorSalaryDTO> amount(int id) {
        List<TutorSalaryDTO> tutorSalaryDTOS = new ArrayList<>();
        List<TutorSalary> tutorSalaries = this.tutorSalaryRepository.findByTutor_School_Id(id);
        for (TutorSalary tutorSalary : tutorSalaries) {
            if (tutorSalary.getAmount() > 2000) {
                TutorSalaryDTO dto = new TutorSalaryDTO();
                dto.setTutorId(tutorSalary.getTutor().getId());
                dto.setTutorName(tutorSalary.getTutor().getName());
                dto.setSchoolId(tutorSalary.getTutor().getSchool().getId());
                dto.setAmount(tutorSalary.getAmount());
                tutorSalaryDTOS.add(dto);
            }
        }
        return tutorSalaryDTOS;
    }


}
