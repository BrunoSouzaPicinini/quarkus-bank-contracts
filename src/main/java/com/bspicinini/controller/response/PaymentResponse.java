package com.bspicinini.controller.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PaymentResponse(Long id, BigDecimal amount, LocalDateTime paymentDate, LocalDateTime createdAt, LocalDateTime updatedAt, Long contractId) {}