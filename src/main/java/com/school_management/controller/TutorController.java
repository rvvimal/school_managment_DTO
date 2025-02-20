package com.school_management.controller;


import com.school_management.dto.ResponseDTO;
import com.school_management.entity.Tutor;
import com.school_management.service.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tutor")
public class TutorController {
    @Autowired
    private TutorService tutorService;

    @PostMapping("/create")
    private ResponseDTO createTutor(@RequestBody final Tutor tutor) {
        return this.tutorService.createTutor(tutor);
    }

    @GetMapping("/retrieve")
    private ResponseDTO getAllTutor() {
        return this.tutorService.getAlltutor();
    }

    @GetMapping("/id/{tutorId}")
    private ResponseDTO getTutorById(@PathVariable final int tutorId) {
        return this.tutorService.findById(tutorId);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDTO deleteTutorById(@PathVariable final int id) {
        return this.tutorService.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseDTO updateById(@PathVariable final int id, @RequestBody final Tutor tutor) {
        return this.tutorService.updateById(tutor, id);

    }

//    @GetMapping("/tutorDetails")
//    public List<TutorSalaryDTO> getTutorBySalary(@RequestParam int id, @RequestParam Double Amount) {
//        return tutorService.getTutorBySalary(id, Amount);
//    }
}

