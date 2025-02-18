package com.school_management.controller;

import com.school_management.dto.ResponseDTO;
import com.school_management.dto.SchoolDetailsDTO;
import com.school_management.dto.SchoolFeeDetailsDTO;
import com.school_management.dto.StudentDetailsDTO;
import com.school_management.entity.Student;
import com.school_management.entity.StudentCourse;
import com.school_management.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/create")
    private ResponseDTO createStudent(@RequestBody final Student student) {
        return this.studentService.createStudent(student);
    }

    @GetMapping("/retrieve")
    private ResponseDTO getAllStudent() {
        return this.studentService.getAllStudent();
    }

    @GetMapping("/{id}")
    private ResponseDTO getStudentById(@PathVariable final int id) {
        return this.studentService.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDTO deleteStudentById(@PathVariable final int id) {
        return this.studentService.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseDTO updateById(@PathVariable final int id, @RequestBody final Student student) {
        return this.studentService.updateById(student, id);
    }

//    @GetMapping("/studentCourseName/{id}")
//    public List<StudentDetailsDTO> getStudent(@PathVariable int id) {
//        return studentService.getStudent(id);
//    }

    @GetMapping("/pagination")
    public Page<Student> getStudentCourse(@RequestParam final int pageIndex, @RequestParam final int pageSize){
        return this.studentService.getStudentCourse(pageIndex,pageSize);
    }
    @GetMapping("/pagination1")
    public Page<Student> getStudentCoursePage(@RequestParam final int pageIndex, @RequestParam final int pageSize,@RequestParam final String field,@RequestParam final boolean direction ){
        return this.studentService.getStudentCoursePage(pageIndex,pageSize,field,direction);
    }

@GetMapping("/search")
    public List<Student>r(@RequestParam(required = false) final String z){
        return this.studentService.search(z);
}
    @GetMapping("/searchPag")
    public Page<Student>page(@RequestParam final int pageIndex, @RequestParam final int pageSize,@RequestParam final String keyword){
        return this.studentService.searchDetails(pageIndex,pageSize,keyword);
    }

}
