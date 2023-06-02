package com.advpro.gpb.controller;

// import java.util.ArrayList;
import java.util.List;
// import java.util.Optional;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.advpro.gpb.exception.PaymentNotFoundException;
import com.advpro.gpb.model.PaymentModel;
import com.advpro.gpb.service.PaymentService;

import jakarta.validation.Valid;

@RestController
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @GetMapping("/getAllPayments")
    public ResponseEntity<List<PaymentModel>> getAllPayments() {
        try {
            List<PaymentModel> paymentList =  paymentService.getAllPayments();
            if (paymentList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(paymentList, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getPaymentById/{id}")
    public ResponseEntity<PaymentModel> getPaymentById(@PathVariable Long id) throws PaymentNotFoundException{
        Optional<PaymentModel> paymentObj = paymentService.getPaymentById(id);
        if (paymentObj.isPresent()) {
            return new ResponseEntity<>(paymentObj.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addPayment")
    public ResponseEntity<PaymentModel> addPayment(@Valid @RequestBody PaymentModel payment){
        try {
            PaymentModel paymentObj = paymentService.addPayment(payment);
            return new ResponseEntity<>(paymentObj, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
