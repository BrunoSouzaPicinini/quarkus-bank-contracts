package com.bspicinini.controller.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record ContractResponse(Long id, BigDecimal amount, Integer installments, Long customerId, List<Long> originContractIds, Long derivedContractId, LocalDateTime createdAt, LocalDateTime updatedAt, String status) {}