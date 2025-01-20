package com.bspicinini.controller.input;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PaymentInput(BigDecimal amount, LocalDateTime paymentDate) {}