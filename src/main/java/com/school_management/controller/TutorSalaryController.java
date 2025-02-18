package com.school_management.controller;


import com.school_management.dto.ResponseDTO;
import com.school_management.dto.TutorSalaryDTO;
import com.school_management.entity.TutorSalary;
import com.school_management.service.TutorSalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tutorSalary")
public class TutorSalaryController {
    @Autowired
    private TutorSalaryService tutorSalaryService;

    @PostMapping("/create")
    private ResponseDTO createTutorSalary(@RequestBody final TutorSalary tutorSalary) {
        return this.tutorSalaryService.createTutorSalary(tutorSalary);
    }

    @GetMapping("/retrieve")
    private ResponseDTO getAllTutorSalary() {
        return this.tutorSalaryService.getAlltutorSalary();
    }

    @GetMapping("/{tutorSalaryId}")
    private ResponseDTO getTutorSalaryById(@PathVariable final int tutorSalaryId) {
        return this.tutorSalaryService.findById(tutorSalaryId);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDTO deleteTutorSalaryById(@PathVariable final int id) {
        return this.tutorSalaryService.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseDTO updateById(@PathVariable final int id, @RequestBody final TutorSalary tutorSalary) {
        return this.tutorSalaryService.updateById(tutorSalary, id);

    }
    @GetMapping("/tutorAmount/{id}")
    public List<TutorSalaryDTO> t(@PathVariable int id){
        return this.tutorSalaryService.sal(id);
    }
}
