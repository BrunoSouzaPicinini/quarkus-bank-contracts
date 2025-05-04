package com.bspicinini.service.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record ContractDto(Long id, Long customerOfferId, BigDecimal amount, Integer numberOfInstallments, List<Long> originContractIds, Long derivedContractId, Long originRenegociationId, LocalDateTime createdAt, LocalDateTime updatedAt, String status) {}