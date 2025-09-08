package com.example.demo.Services;

import com.example.demo.entity.Payment;
import com.example.demo.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    @Autowired private PaymentRepository paymentRepository;
    public Payment createPayment(Payment payment){
        payment.setStatus("PAID");
        payment.setGatewayTransactionId("MOCK-" + System.currentTimeMillis());
        return paymentRepository.save(payment);
    }
}
