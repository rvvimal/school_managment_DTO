package com.school_management.controller;

import com.school_management.dto.ResponseDTO;
import com.school_management.entity.TutorCourse;
import com.school_management.service.TutorCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tutorCourse")
public class TutorCourseController {
    @Autowired
    private TutorCourseService tutorCourseService;

    @PostMapping("/create")
    private ResponseDTO createTutorCourse(@RequestBody final TutorCourse tutorCourse) {
        return this.tutorCourseService.createTutorCourse(tutorCourse);
    }

    @GetMapping("/retrieve")
    private ResponseDTO getAllTutorCourse() {
        return this.tutorCourseService.getAlltutorCourse();
    }

    @GetMapping("/{tutorCourseId}")
    private ResponseDTO getTutorCourseById(@PathVariable final int tutorCourseId) {
        return this.tutorCourseService.findById(tutorCourseId);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDTO deleteTutorCourseById(@PathVariable final int id) {
        return this.tutorCourseService.deleteById(id);
    }

}