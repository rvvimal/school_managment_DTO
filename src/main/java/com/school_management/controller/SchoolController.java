package com.school_management.controller;

import com.school_management.dto.*;
import com.school_management.entity.School;
import com.school_management.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/school")
public class SchoolController {
    @Autowired
    private SchoolService schoolService;

    @PostMapping("/create")
    private ResponseDTO createSchool(@RequestBody final School school) {
        return this.schoolService.createSchool(school);
    }

    @GetMapping("/retrieve")
    private ResponseDTO getAllSchool() {
        return this.schoolService.getAllSchool();
    }

    @GetMapping("/{schoolId}")
    private ResponseDTO getSchoolById(@PathVariable final int schoolId) {
        return this.schoolService.findById(schoolId);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDTO deleteSchoolById(@PathVariable final int id) {
        return this.schoolService.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseDTO updateById(@PathVariable final int id, @RequestBody final School school) {
        return this.schoolService.updateById(school, id);
    }
//    @GetMapping("/schoolDetails/{id}")
//    public List<SchoolDetailsDTO> getStudentAndTutorAndSchoolId(@PathVariable("id") int id) {
//        return schoolService.getStudentAndTutorAndSchoolId(id);
//    }
//    @GetMapping("/courseCount/{id}")
//    public List<SchoolDTO>getCountSchool(){
//        return schoolService.getCountSchool();
//    }
//@GetMapping("/feeDetails/{id}")
//    public List<SchoolFeeDetailsDTO>getSchoolFeeDetails(){
//        return schoolService.getSchoolFeeDetails();
//}

}
