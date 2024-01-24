package com.example.demo.ServiceImpl;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.TransactionDetails;
import com.example.demo.Model.PaymentRequest;
import com.example.demo.Repository.TransactionDetailsRepo;
import com.example.demo.Service.PaymentService;

import lombok.extern.log4j.Log4j2;


@Service
@Log4j2
public class PaymentServiceImpl implements PaymentService {

	
	@Autowired
	private TransactionDetailsRepo tr;
	
	
	@Override
	public long doPayment(PaymentRequest pr) {
		log.info("Recording payment Details: {}", pr);
		
		TransactionDetails td = TransactionDetails.builder()
				.paymentDate(Instant.now())
				.paymentMode(pr.getPaymentMode().name())
				.paymentStatus("SUCCESS")
				.orderId(pr.getOrderId())
				.referenceNumber(pr.getReferenceNumber())
				.amount(pr.getAmount())
				.build();
		
		tr.save(td);
		log.info("Transaction Completed with Id: {}", td.getId());
		return td.getId();
	}

}
