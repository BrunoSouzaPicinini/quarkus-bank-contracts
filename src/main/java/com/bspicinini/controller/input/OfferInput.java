package com.bspicinini.controller.input;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.bspicinini.repository.entity.OfferScope;
import com.bspicinini.repository.entity.OfferType;

public record OfferInput(
	BigDecimal interestRate,
	OfferType type,
	Integer daysOverdue,
	Integer maxInstallments,
	Integer minInstallments,
	OfferScope scope,
	LocalDateTime startDate,
	LocalDateTime expirationDate
) {}
