package com.bspicinini.service;

import com.bspicinini.controller.input.PaymentInput;
import com.bspicinini.mapper.PaymentMapper;
import com.bspicinini.repository.PaymentRepository;
import com.bspicinini.repository.entity.Payment;
import com.bspicinini.service.dto.PaymentDto;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public List<PaymentDto> findAllPayments() {
        return paymentRepository.listAll().stream()
                .map(PaymentMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    public PaymentDto findPaymentById(Long id) {
        Payment payment = paymentRepository.findById(id);
        return PaymentMapper.INSTANCE.toDto(payment);
    }

    public PaymentDto createPayment(PaymentInput paymentInput) {
        Payment payment = PaymentMapper.INSTANCE.toEntity(paymentInput);
        paymentRepository.persist(payment);
        return PaymentMapper.INSTANCE.toDto(payment);
    }

    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }
}