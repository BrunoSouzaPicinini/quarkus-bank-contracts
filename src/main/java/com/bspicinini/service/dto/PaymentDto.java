package com.bspicinini.service.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PaymentDto(Long id, BigDecimal amount, LocalDateTime paymentDate, Long installmentId, LocalDateTime createdAt, LocalDateTime updatedAt) {}