package com.school_management.controller;

import com.school_management.dto.ResponseDTO;
import com.school_management.dto.SchoolFeeDetailsDTO;
import com.school_management.entity.FeePayment;
import com.school_management.service.FeePaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/feePayment")
public class FeePaymentController {
    @Autowired
    private FeePaymentService feePaymentService;

    @PostMapping("/create")
    private ResponseDTO createFeePayment(@RequestBody final FeePayment feePayment) {
        return this.feePaymentService.createFeePayment(feePayment);
    }

    @GetMapping("/retrieve")
    private ResponseDTO getAllFeePayment() {
        return this.feePaymentService.getAllFeePayment();
    }

    @GetMapping("/{feePaymentId}")
    private ResponseDTO getFeePaymentById(@PathVariable final int feePaymentId) {
        return this.feePaymentService.findById(feePaymentId);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDTO deleteFeePaymentById(@PathVariable final int id) {
        return this.feePaymentService.deleteById(id);
    }

    @PutMapping("/update/{amount}")
    public ResponseDTO updateById(@PathVariable final int amount , @RequestBody final FeePayment feePayment) {
        return this.feePaymentService.updateById(feePayment,amount);
    }
    @GetMapping("/sc/{id}")
    public List<SchoolFeeDetailsDTO> t(@PathVariable int id) {
        return this.feePaymentService.saler(id);
    }
}
