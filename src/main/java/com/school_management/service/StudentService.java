package com.school_management.service;

import com.school_management.dto.ResponseDTO;
import com.school_management.dto.SchoolDetailsDTO;
import com.school_management.dto.SchoolFeeDetailsDTO;
import com.school_management.entity.Student;
import com.school_management.entity.StudentCourse;
import com.school_management.exception.UserNotFoundException;
import com.school_management.repository.StudentRepository;
import com.school_management.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Transactional
    public ResponseDTO createStudent(final Student student) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.CREATE,
                this.studentRepository.save(student));
    }

    public ResponseDTO getAllStudent() {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE,
                this.studentRepository.findAll());
    }

    public ResponseDTO findById(final int id) {
        if (!this.studentRepository.existsById(id)) {
            throw new UserNotFoundException(Constant.ID_DOES_NOT_EXIST);
        }
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE,
                this.studentRepository.findById(id));
    }

    public ResponseDTO deleteById(final int id) {
        if (this.studentRepository.existsById(id)) {
            this.studentRepository.deleteById(id);
        }
        return new ResponseDTO(HttpStatus.OK.value(), Constant.DELETE,
                Constant.REMOVE);
    }


    @Transactional
    public ResponseDTO updateById(final Student student, final int id) {
        final Student studentObject = this.studentRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(Constant.ID_DOES_NOT_EXIST));
        if (student.getFirstName() != null) {
            studentObject.setFirstName(student.getFirstName());
        }
        if (student.getLastName() != null) {
            studentObject.setLastName(student.getLastName());
        }
        if (student.getEmail() != null) {
            studentObject.setEmail(student.getEmail());
        }
        if (student.getEnrollmentDate() != null) {
            studentObject.setEnrollmentDate(student.getEnrollmentDate());
        }
        if (student.getContactNumber() != 0) {
            studentObject.setContactNumber(student.getContactNumber());
        }

        return new ResponseDTO(HttpStatus.OK.value(), Constant.UPDATE,
                this.studentRepository.save(studentObject));
    }

//    public List<StudentDetailsDTO>getStudent(int id){
//        return studentRepository.findStudent(id);
//    }


    public Page<Student> getStudentCourse(final int pageIndex, final int pageSize) {
        Pageable pageable = PageRequest.of(pageIndex-1, pageSize);
        return this.studentRepository.findAll(pageable);
    }

    public Page<Student> getStudentCoursePage(final int pageIndex, final int pageSize,final String field,final boolean direction) {
        Pageable pageable = PageRequest.of(pageIndex, pageSize, Sort.by(direction ? Sort.Direction.ASC : Sort.Direction.DESC, field));
//        Pageable pageable = PageRequest.of(pageIndex, pageSize,sort);
        return this.studentRepository.findAll(pageable);
    }
    public List<Student>search(String z){
//        if(z==null||z.trim().isEmpty()){
//            return null;
//        }else {
            return this.studentRepository.findByStudent_SchoolName(z);
//        }
    }
    public Page<Student>searchDetails(int pageIndex,int pageSize,String keyword){
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        return this.studentRepository.findByStudent_School(keyword,pageable);

    }

}
