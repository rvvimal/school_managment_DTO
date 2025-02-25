package com.school_management.controller;

import com.school_management.dto.ResponseDTO;
import com.school_management.entity.School;
import com.school_management.service.SchoolService;
import com.school_management.util.Constant;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class SchoolController {
    @Autowired
    private SchoolService schoolService;

    @PostMapping("/school")
    private ResponseDTO createSchool(@Valid @RequestBody final School school) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.CREATE, this.schoolService.createSchool(school));
    }

    @GetMapping("/school")
    private ResponseDTO getAllSchool() {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE, this.schoolService.getAllSchool());
    }

    @GetMapping("/school/{id}")
    private ResponseDTO getSchoolById(@PathVariable final int id) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE, this.schoolService.findById(id));
    }

    @DeleteMapping("/school/{id}")
    public ResponseDTO deleteSchoolById(@PathVariable final int id) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.DELETE, this.schoolService.deleteById(id));
    }

    @PutMapping("/school/{id}")
    public ResponseDTO updateById(@Valid @PathVariable final int id, @RequestBody final School school) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.UPDATE, this.schoolService.updateById(school, id));
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
