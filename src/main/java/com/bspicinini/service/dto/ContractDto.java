package com.bspicinini.service.dto;

import java.math.BigDecimal;

public record ContractDto(Long id, String contractNumber, BigDecimal amount) {}