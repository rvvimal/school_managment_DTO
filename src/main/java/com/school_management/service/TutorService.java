package com.school_management.service;

import com.school_management.dto.ResponseDTO;
import com.school_management.entity.Tutor;
import com.school_management.exception.UserNotFoundException;
import com.school_management.repository.TutorRepository;
import com.school_management.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TutorService {
    @Autowired
    private TutorRepository tutorRepository;

    @Transactional
    public ResponseDTO createTutor(final Tutor tutor) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.CREATE, this.tutorRepository.save(tutor));
    }

    public ResponseDTO getAlltutor() {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE, this.tutorRepository.findAll());
    }

    public ResponseDTO findById(final int id) {
        if (!this.tutorRepository.existsById(id)) {
            throw new UserNotFoundException(Constant.DATA_NULL);
        }
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE, this.tutorRepository.findById(id));
    }

    public ResponseDTO deleteById(final int id) {
        if (this.tutorRepository.existsById(id)) {
            this.tutorRepository.deleteById(id);
        }
        return new ResponseDTO(HttpStatus.OK.value(), Constant.DELETE, Constant.REMOVE);
    }

    @Transactional
    public ResponseDTO updateById(final Tutor tutor, final int id) {
        final Tutor tutorObject = this.tutorRepository.findById(id).orElseThrow(() -> new UserNotFoundException(Constant.ID_DOES_NOT_EXIST));

        if (tutor.getName() != null) {
            tutorObject.setName(tutor.getName());
        }
        if (tutor.getEmail() != null) {
            tutorObject.setEmail(tutor.getEmail());
        }
        if (tutor.getContactNumber() != 0) {
            tutorObject.setContactNumber(tutor.getContactNumber());
        }
        return new ResponseDTO(HttpStatus.OK.value(), Constant.UPDATE, this.tutorRepository.save(tutorObject));
    }


//    public List<TutorSalaryDTO> getTutorBySalary(int id, double Amount){
//        return tutorRepository.findTutorBySalary(id,Amount);
//    }

}

