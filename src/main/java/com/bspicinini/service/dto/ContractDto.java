package com.bspicinini.service.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record ContractDto(Long id, String contractNumber, BigDecimal amount, Integer installments, Long customerId, List<Long> originContractIds, Long derivedContractId, LocalDateTime createdAt, LocalDateTime updatedAt, String status) {}