package com.school_management.controller;

import com.school_management.dto.ResponseDTO;
import com.school_management.entity.Course;
import com.school_management.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping("/create")
    private ResponseDTO createCourse(@RequestBody final Course course) {
        return this.courseService.createCourse(course);
    }

    @GetMapping("/retrieve")
    private ResponseDTO getAllCourse() {
        return this.courseService.getAllCourse();
    }

    @GetMapping("/{courseId}")
    private ResponseDTO getCourseById(@PathVariable final int courseId) {
        return this.courseService.findById(courseId);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDTO deleteCourseById(@PathVariable final int id) {
        return this.courseService.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseDTO updateById(@PathVariable final int id, @RequestBody final Course course) {
        return this.courseService.updateById(course, id);
    }
}
