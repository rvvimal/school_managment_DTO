package com.school_management.service;

import com.school_management.dto.ResponseDTO;
import com.school_management.dto.SchoolDTO;
import com.school_management.dto.SchoolDetailsDTO;
import com.school_management.dto.StudentCourseDTO;
import com.school_management.entity.School;
import com.school_management.entity.StudentCourse;
import com.school_management.exception.UserNotFoundException;
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
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE,
                this.studentCourseRepository.findById(id));
    }


    public ResponseDTO deleteById(final int id) {
        if (this.studentCourseRepository.existsById(id)) {
            this.studentCourseRepository.deleteById(id);
        }
        return new ResponseDTO(HttpStatus.OK.value(), Constant.DELETE,
                Constant.REMOVE);
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

    public Page<StudentCourse> getStudentCoursePage(final int pageIndex, final int pageSize,final String field) {
        Sort sort = Sort.by(Sort.Direction.ASC ,field);
        Pageable pageable = PageRequest.of(pageIndex, pageSize,sort);
        return this.studentCourseRepository.findAll(pageable);
    }

    public List<SchoolDetailsDTO> sals(int id) {
        List<SchoolDetailsDTO> SchoolDetailsDTOS = new ArrayList<>();
        List<StudentCourse> student = this.studentCourseRepository.findByStudent_School_Id(id);
        for (StudentCourse school1 : student) {
            SchoolDetailsDTO dto = new SchoolDetailsDTO();
            dto.setId(school1.getId());
            dto.setSchoolName(school1.getStudent().getSchool().getName());
            dto.setTutorId(school1.getId());
            dto.setTutorName(school1.getTutor().getName());
            dto.setCourseId(school1.getId());
            dto.setCourseName(school1.getCourse().getName());
            dto.setStudentId(school1.getId());
            dto.setStudentFirstName(school1.getStudent().getFirstName());
            dto.setStudentLastName(school1.getStudent().getLastName());
            dto.setEnrollmentDate(school1.getStudent().getEnrollmentDate());
            SchoolDetailsDTOS.add(dto);
        }
        return SchoolDetailsDTOS;
    }

public List<SchoolDTO> salary(Integer id) {
    List<SchoolDTO> schoolDetailsDTOS = new ArrayList<>();
    List<StudentCourse> students = this.studentCourseRepository.findByStudent_School_Id(id);

    if (!students.isEmpty()) {
        School school = students.get(0).getStudent().getSchool();

        SchoolDTO schoolDTO = new SchoolDTO();
        schoolDTO.setSchoolId(school.getId());
        schoolDTO.setSchoolName(school.getName());
        schoolDTO.setTotalCourse(students.size());

        schoolDetailsDTOS.add(schoolDTO);
    }

    return schoolDetailsDTOS;
}

}


