package com.bspicinini.service.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record ContractDto(Long id, BigDecimal amount, Integer numberOfInstallments, Long customerId, List<Long> originContractIds, Long derivedContractId, LocalDateTime createdAt, LocalDateTime updatedAt, String status) {}