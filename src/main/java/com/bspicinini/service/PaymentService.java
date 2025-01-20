package com.bspicinini.service;

import com.bspicinini.controller.input.PaymentInput;
import com.bspicinini.mapper.PaymentMapper;
import com.bspicinini.repository.PaymentRepository;
import com.bspicinini.repository.entity.Contract;
import com.bspicinini.repository.entity.Payment;
import com.bspicinini.service.dto.PaymentDto;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper mapper;

    public PaymentService(PaymentRepository paymentRepository, PaymentMapper mapper) {
        this.paymentRepository = paymentRepository;
        this.mapper = mapper;
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

    @Transactional
    public PaymentDto createPayment(PaymentInput paymentInput) {
        Payment payment = PaymentMapper.INSTANCE.toEntity(paymentInput);
        var contract = new Contract();
        contract.setId(paymentInput.contractId());

        payment.setContract(contract);

        paymentRepository.persist(payment);
        return mapper.toDto(payment);
    }

}