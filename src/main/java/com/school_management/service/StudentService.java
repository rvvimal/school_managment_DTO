package com.school_management.service;

import com.school_management.dto.PaginationResponse;
import com.school_management.dto.StudentDetailsDTO;
import com.school_management.entity.Student;
import com.school_management.exception.UserNotFoundException;
import com.school_management.repository.StudentRepository;
import com.school_management.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Transactional
    public Student createStudent(final Student student) {
        return this.studentRepository.save(student);
    }

    public List<Student> getAllStudent() {
        return this.studentRepository.findAll();
    }

    public Student findById(final int id) {
        return this.studentRepository.findById(id).orElseThrow(() -> new RuntimeException(Constant.ID_DOES_NOT_EXIST));

    }

    public String deleteById(final int id) {
        if (this.studentRepository.existsById(id)) {
            this.studentRepository.deleteById(id);
        } else {
            throw new UserNotFoundException(Constant.NOT_FOUND + " " + id);
        }
        return Constant.REMOVE;
    }


    @Transactional
    public Student updateById(final Student student, final int id) {
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

        return this.studentRepository.save(studentObject);
    }

    //    public Page<Student> getStudentCourse(final int pageIndex, final int pageSize) {
//        Pageable pageable = PageRequest.of(pageIndex - 1, pageSize);
//        return this.studentRepository.findAll(pageable);
//    }


    public PaginationResponse getSortedStudentPage(final int pageIndex, final int pageSize, final String field, final boolean sort) {
        if (pageIndex < 0 || pageSize <= 0) {
            throw new RuntimeException(Constant.NOT_FOUND);
        }
        Pageable pageable = PageRequest.of(pageIndex, pageSize, Sort.by(sort ? Sort.Direction.ASC : Sort.Direction.DESC, field));
        Page<Student> studentPage = this.studentRepository.findAll(pageable);
        return new PaginationResponse(
                studentPage.getTotalPages(),
                studentPage.getTotalElements(),
                studentPage.getSize(),
                studentPage.getContent()
        );
    }


//    public PageDTO getStudentCoursePage2(final int pageIndex, final int pageSize, final String field, final boolean direction) {
//        Pageable pageable = PageRequest.of(pageIndex, pageSize, Sort.by(direction ? Sort.Direction.ASC : Sort.Direction.DESC, field));
//        Page<Student> studentPage=this.studentRepository.findAll(pageable);
//        return  new PageDTO(
//                studentPage.getTotalPages(),
//                studentPage.getTotalElements(),
//                studentPage.getSize(),
//                studentPage.getContent()
//        );
//
//    }


//public List<Student> getStudentCoursePage(final int pageIndex, final int pageSize, final String field, final boolean direction) {
//    String sortField = (field == null || field.isEmpty()) ? "id" : field;
//    Pageable pageable = PageRequest.of(pageIndex, pageSize, Sort.by(direction ? Sort.Direction.ASC : Sort.Direction.DESC, sortField));
//    return this.studentRepository.findAll(pageable).toList();
//}

//    public List<Student> searchStudents(String keyword) {
////        return this.studentRepository.findByStudentAndSchoolName(keyword);
////    }
//
//    public Page<Student> getStudentsBySearchPage(int pageIndex, int pageSize, String keyword) {
//        Pageable pageable = PageRequest.of(pageIndex, pageSize);
//        return this.studentRepository.findByStudentAndSchool(keyword, pageable);
//
//    }

    public List<StudentDetailsDTO> getStudentWithCourses(int id) {
        return studentRepository.findStudentWithCourses(id);
    }
}
