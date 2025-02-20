package com.school_management.controller;

import com.school_management.dto.ResponseDTO;
import com.school_management.dto.SchoolDTO;
import com.school_management.dto.SchoolDetailsDTO;
import com.school_management.entity.StudentCourse;
import com.school_management.service.StudentCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/studentCourse")
public class StudentCourseController {

    @Autowired
    private StudentCourseService studentCourseService;

    @PostMapping("/create")
    private ResponseDTO createStudentCourse(@RequestBody final StudentCourse studentCourse) {
        return this.studentCourseService.createStudentCourse(studentCourse);
    }

    @GetMapping("/retrieve")
    private ResponseDTO getAllStudentCourse() {
        return this.studentCourseService.getAllStudentCourse();
    }

    @GetMapping("/{studentCourseId}")
    private ResponseDTO getStudentCourseById(@PathVariable final int studentCourseId) {
        return this.studentCourseService.findById(studentCourseId);
    }

    @GetMapping("/retrieve-all-student")
    public ResponseDTO retrieveAllStudentCourseDetail() {
        return this.studentCourseService.retrieveAllStudentDetail();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDTO deleteStudentCourseById(@PathVariable final int id) {
        return this.studentCourseService.deleteById(id);
    }

    @GetMapping("/pagination")
    public Page<StudentCourse> getStudentCoursePage(@RequestParam final int pageIndex, @RequestParam final int pageSize, @RequestParam String field) {
        return this.studentCourseService.getStudentCoursePage(pageIndex, pageSize, field);
    }

    @GetMapping("/pageSchool/{schoolId}")
    public Page<SchoolDetailsDTO> pages(@PathVariable int schoolId, @RequestParam final int pageIndex, @RequestParam final int pageSize) {
        return this.studentCourseService.pages(schoolId, pageIndex, pageSize);
    }

    @GetMapping("/countCourse/{id}")
    public SchoolDTO count(@PathVariable Integer id) {
        return this.studentCourseService.getCount(id);
    }
}