package com.school_management.service;

import com.school_management.dto.ResponseDTO;
import com.school_management.entity.Course;
import com.school_management.exception.UserNotFoundException;
import com.school_management.repository.CourseRepository;
import com.school_management.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Transactional
    public ResponseDTO createCourse(final Course course) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.CREATE, this.courseRepository.save(course));
    }

    public ResponseDTO getAllCourse() {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE, this.courseRepository.findAll());
    }

    public ResponseDTO findById(final int id) {
        if (!this.courseRepository.existsById(id)) {
            throw new UserNotFoundException(Constant.ID_DOES_NOT_EXIST);
        }
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE, this.courseRepository.findById(id));
    }


    public ResponseDTO deleteById(final int id) {
        if (this.courseRepository.existsById(id)) {
            this.courseRepository.deleteById(id);
        } else {
            throw new UserNotFoundException(Constant.FOUND);
        }
        return new ResponseDTO(HttpStatus.OK.value(), Constant.DELETE, Constant.REMOVE);
    }

    @Transactional
    public ResponseDTO updateById(final Course course, final int id) {
        final Course courseObject = this.courseRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(Constant.ID_DOES_NOT_EXIST));
        if (course.getName() != null) {
            courseObject.setName(course.getName());
        }
        return new ResponseDTO(HttpStatus.OK.value(), Constant.UPDATE, this.courseRepository.save(courseObject));
    }
}

