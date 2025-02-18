package com.school_management.service;


import com.school_management.dto.ResponseDTO;
import com.school_management.dto.SchoolDTO;
import com.school_management.entity.School;
import com.school_management.entity.TutorCourse;
import com.school_management.exception.UserNotFoundException;
import com.school_management.repository.TutorCourseRepository;
import com.school_management.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class TutorCourseService {
    @Autowired
    private TutorCourseRepository tutorCourseRepository;
@Transactional
    public ResponseDTO createTutorCourse(final TutorCourse tutorCourse) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.CREATE, this.tutorCourseRepository.save(tutorCourse));
    }

    public ResponseDTO getAlltutorCourse() {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE, this.tutorCourseRepository.findAll());
    }

    public ResponseDTO findById(final int id) {
        if (!this.tutorCourseRepository.existsById(id)) {
            throw new UserNotFoundException(Constant.ID_DOES_NOT_EXIST);
        }
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE,
                this.tutorCourseRepository.findById(id));
    }


    public ResponseDTO deleteById(final int id) {
        if (this.tutorCourseRepository.existsById(id)) {
            this.tutorCourseRepository.deleteById(id);
        }
        return new ResponseDTO(HttpStatus.OK.value(), Constant.DELETE,
                Constant.REMOVE);
    }


}