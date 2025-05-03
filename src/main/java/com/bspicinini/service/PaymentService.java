package com.bspicinini.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.bspicinini.controller.input.PaymentInput;
import com.bspicinini.exception.BusinessException;
import com.bspicinini.mapper.PaymentMapper;
import com.bspicinini.repository.PaymentRepository;
import com.bspicinini.repository.entity.Installment;
import com.bspicinini.repository.entity.InstallmentStatus;
import com.bspicinini.repository.entity.Payment;
import com.bspicinini.service.dto.PaymentDto;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

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
        var installment = new Installment();
        installment.setId(paymentInput.installmentId());
        payment.setInstallment(installment);

        List<Payment> installmentPayments = paymentRepository.findPaymentsByInstallmentId(paymentInput.installmentId());

        if (installmentPayments.size() > 0) {
            BigDecimal totalPaid = installmentPayments.stream()
                    .map(Payment::getAmount)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            var amountCompareResult = totalPaid.add(payment.getAmount())
                    .compareTo(payment.getInstallment().getBaseValue());
            if (amountCompareResult == 1) {
                throw new BusinessException("The total paid amount will be greater than the installment base value");
            } else if (amountCompareResult == 0) {
                payment.getInstallment().setStatus(InstallmentStatus.PAID);
            } 
        }

        paymentRepository.persist(payment);
        return mapper.toDto(payment);
    }

}