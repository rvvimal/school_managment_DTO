package com.school_management.controller;

import com.school_management.dto.ResponseDTO;
import com.school_management.entity.Student;
import com.school_management.service.StudentService;
import com.school_management.util.Constant;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/student")
    private ResponseDTO createStudent(@Valid @RequestBody final Student student) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.CREATE, this.studentService.createStudent(student));
    }

    @GetMapping("/student")
    private ResponseDTO getAllStudent() {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE, this.studentService.getAllStudent());
    }

    @GetMapping("/student/{id}")
    private ResponseDTO getStudentById(@PathVariable final int id) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE, this.studentService.findById(id));
    }

    @DeleteMapping("/student/{id}")
    public ResponseDTO deleteStudentById(@PathVariable final int id) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.DELETE, this.studentService.deleteById(id));
    }

    @PutMapping("/student/{id}")
    public ResponseDTO updateById(@Valid @PathVariable final int id, @RequestBody final Student student) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.UPDATE, this.studentService.updateById(student, id));
    }

//    @GetMapping("/studentCourseName/{id}")
//    public List<StudentDetailsDTO> getStudent(@PathVariable int id) {
//        return studentService.getStudent(id);
//    }

//    @GetMapping("/data/pagination")
//    public ResponseDTO getStudentCourse(@RequestParam final int pageIndex, @RequestParam final int pageSize) {
//        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE, this.studentService.getStudentCourse(pageIndex, pageSize));
//    }

    @GetMapping("/student/page/sorted")
    public ResponseDTO getStudentCoursePage(@RequestParam(defaultValue = "0") final int pageIndex, @RequestParam(defaultValue = "5") final int pageSize, @RequestParam(defaultValue = "ASC") boolean sort, @RequestParam(defaultValue = "id") String field) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE, this.studentService.getSortedStudentPage(pageIndex, pageSize, field, sort));

    }
//
//    @GetMapping("/student/search")
//    public ResponseDTO search(@RequestParam final String keyword) {
//        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE, this.studentService.searchStudents(keyword));
//    }

//    @GetMapping("/student/search/page ")
//    public ResponseDTO page(@RequestParam final int pageIndex, @RequestParam final int pageSize, @RequestParam final String keyword) {
//        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE, this.studentService.getStudentsBySearchPage(pageIndex, pageSize, keyword));
//    }

    @GetMapping("/student/{id}/courses")
    public ResponseDTO getStudent(@PathVariable int id) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE, studentService.getStudentWithCourses(id));
    }
}
