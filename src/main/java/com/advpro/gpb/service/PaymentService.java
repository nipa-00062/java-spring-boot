package com.advpro.gpb.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.advpro.gpb.exception.PaymentNotFoundException;
import com.advpro.gpb.model.PaymentModel;
import com.advpro.gpb.repository.PaymentRepository;

@Service
public class PaymentService {
	
	@Autowired
	private PaymentRepository paymentRepository;

	public List<PaymentModel> getAllPayments() {
        List<PaymentModel> paymentList = new ArrayList<>();
        paymentRepository.findAll().forEach(paymentList::add);
        return paymentList;

	}	
	public PaymentModel addPayment(PaymentModel payment) {
		return paymentRepository.save(payment);
	}	
	public Optional<PaymentModel> getPaymentById(@PathVariable Long id) throws PaymentNotFoundException{
		Optional<PaymentModel> paymentObj = paymentRepository.findById(id);
		if(paymentObj!=null){
            return paymentObj;
        }else{
            throw new PaymentNotFoundException("Payment not found with id : " +id);
        }
		
	}	
	
}