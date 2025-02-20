package com.school_management.service;

import com.school_management.dto.ResponseDTO;
import com.school_management.dto.SchoolDTO;
import com.school_management.dto.SchoolDetailsDTO;
import com.school_management.dto.StudentCourseDTO;
import com.school_management.entity.StudentCourse;
import com.school_management.exception.UserNotFoundException;
import com.school_management.repository.FeePaymentRepository;
import com.school_management.repository.StudentCourseRepository;
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
public class StudentCourseService {
    @Autowired
    private StudentCourseRepository studentCourseRepository;
    @Autowired
    private FeePaymentRepository feePaymentRepository;

    @Transactional
    public ResponseDTO createStudentCourse(final StudentCourse studentCourse) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.CREATE, this.studentCourseRepository.save(studentCourse));
    }

    public ResponseDTO getAllStudentCourse() {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE, this.studentCourseRepository.findAll());
    }

    public ResponseDTO findById(final int id) {
        if (!this.studentCourseRepository.existsById(id)) {
            throw new UserNotFoundException(Constant.ID_DOES_NOT_EXIST);
        }
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE, this.studentCourseRepository.findById(id));
    }


    public ResponseDTO deleteById(final int id) {
        if (this.studentCourseRepository.existsById(id)) {
            this.studentCourseRepository.deleteById(id);
        }
        return new ResponseDTO(HttpStatus.OK.value(), Constant.DELETE, Constant.REMOVE);
    }

    public ResponseDTO retrieveAllStudentDetail() {
        List<StudentCourse> course = this.studentCourseRepository.findAll();
        List<StudentCourseDTO> studentDetails = new ArrayList<>();
        for (StudentCourse student1 : course) {
            StudentCourseDTO studentDTO = new StudentCourseDTO();
            studentDTO.setCourseName(student1.getCourse().getName());
            studentDTO.setStudentName(student1.getStudent().getFirstName());
            studentDTO.setTutorName(student1.getTutor().getName());
            studentDTO.setSchoolName(student1.getStudent().getSchool().getName());
            studentDetails.add(studentDTO);
        }
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE, studentDetails);


    }

    public Page<StudentCourse> getStudentCoursePage(final int pageIndex, final int pageSize, final String field) {
        Sort sort = Sort.by(Sort.Direction.ASC, field);
        Pageable pageable = PageRequest.of(pageIndex, pageSize, sort);
        return this.studentCourseRepository.findAll(pageable);
    }

    public Page<SchoolDetailsDTO> pages(int schoolId, int pageIndex, int pageSize) {
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        Page<StudentCourse> studentCourses = studentCourseRepository.findByStudent_School_Id(schoolId, pageable);

        return studentCourses.map(school1 -> {
            SchoolDetailsDTO dto = new SchoolDetailsDTO();
            dto.setId(school1.getId());
            dto.setSchoolName(school1.getStudent().getSchool().getName());
            dto.setTutorId(school1.getTutor().getId());
            dto.setTutorName(school1.getTutor().getName());
            dto.setCourseId(school1.getCourse().getId());
            dto.setCourseName(school1.getCourse().getName());
            dto.setStudentId(school1.getStudent().getId());
            dto.setStudentFirstName(school1.getStudent().getFirstName());
            dto.setStudentLastName(school1.getStudent().getLastName());
            dto.setEnrollmentDate(school1.getStudent().getEnrollmentDate());
            return dto;
        });
    }

//    public List<SchoolDetailsDTO> getSchoolDetails(int schoolId, int pageIndex, int pageSize) {
//        Pageable pageable = PageRequest.of(pageIndex, pageSize);
//        Page<StudentCourse> studentCourses = studentCourseRepository.findByStudent_School_Id(schoolId, pageable);
//
//        List<SchoolDetailsDTO> dtoList = new ArrayList<>();
//
//        for (StudentCourse course : studentCourses) {
//            SchoolDetailsDTO dto = new SchoolDetailsDTO();
//            dto.setId(course.getId());
//            dto.setSchoolName(course.getStudent().getSchool().getName());
//            dto.setTutorId(course.getTutor().getId());
//            dto.setTutorName(course.getTutor().getName());
//            dto.setCourseId(course.getCourse().getId());
//            dto.setCourseName(course.getCourse().getName());
//            dto.setStudentId(course.getStudent().getId());
//            dto.setStudentFirstName(course.getStudent().getFirstName());
//            dto.setStudentLastName(course.getStudent().getLastName());
//            dto.setEnrollmentDate(course.getStudent().getEnrollmentDate());
//
//            dtoList.add(dto);
//        }
//
//        return dtoList;
//    }

    public SchoolDTO getCount(Integer id) {
        List<StudentCourse> students = this.studentCourseRepository.findByStudent_School_Id(id);

        if (!students.isEmpty()) {
            SchoolDTO schoolDTO = new SchoolDTO();
            schoolDTO.setSchoolId(students.get(0).getStudent().getSchool().getId());
            schoolDTO.setSchoolName(students.get(0).getStudent().getSchool().getName());
            schoolDTO.setTotalCourse(students.size());

            return schoolDTO;
        }
        return null;
    }
}