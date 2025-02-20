package com.school_management.service;

import com.school_management.dto.ResponseDTO;
import com.school_management.entity.School;
import com.school_management.exception.UserNotFoundException;
import com.school_management.repository.SchoolRepository;
import com.school_management.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class SchoolService {
    @Autowired
    private SchoolRepository schoolRepository;

    @Transactional
    public ResponseDTO createSchool(final School school) {
        return new ResponseDTO(HttpStatus.OK.value(),
                Constant.CREATE, this.schoolRepository.save(school));
    }

    public ResponseDTO getAllSchool() {
        return new ResponseDTO(HttpStatus.OK.value(),
                Constant.RETRIEVE, this.schoolRepository.findAll());
    }

    public ResponseDTO findById(final int id) {
        if (!this.schoolRepository.existsById(id)) {
            throw new UserNotFoundException(Constant.FOUND);
        }
        return new ResponseDTO(HttpStatus.OK.value(),
                Constant.RETRIEVE, this.schoolRepository.findById(id));
    }


    public ResponseDTO deleteById(final int id) {
        if (this.schoolRepository.existsById(id)) {
            this.schoolRepository.deleteById(id);
            return new ResponseDTO(HttpStatus.OK.value(), Constant.DELETE, Constant.REMOVE);
        } else {
            throw new UserNotFoundException(Constant.NOT_FOUND);
        }
    }

    @Transactional
    public ResponseDTO updateById(final School school, final int id) {
        final School schoolObject = this.schoolRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(Constant.ID_DOES_NOT_EXIST));

        if (school.getName() != null) {
            schoolObject.setName(school.getName());
        }
        if (school.getAddress() != null) {
            schoolObject.setAddress(school.getAddress());
        }
        if (school.getContact() != 0) {
            schoolObject.setContact(school.getContact());
        }
        return new ResponseDTO(HttpStatus.OK.value(),
                Constant.UPDATE, this.schoolRepository.save(schoolObject));
    }
//
//    public List<SchoolDetailsDTO> getStudentAndTutorAndSchoolId(int id) {
//        return schoolRepository.findStudentAndTutorAndSchoolId(id);
//    }
//    public List<SchoolDTO>getCountSchool(){
//        return schoolRepository.findCountSchool();
//    }
//    public List<SchoolFeeDetailsDTO>getSchoolFeeDetails(){
//        return schoolRepository.findSchoolFeeDetails();
//    }

}
