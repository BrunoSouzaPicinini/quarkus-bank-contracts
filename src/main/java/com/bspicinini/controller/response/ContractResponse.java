package com.bspicinini.controller.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

//TODO change to show installments generated
public record ContractResponse(Long id, Long customerOfferId, BigDecimal amount, Integer numberOfInstallments, List<Long> originContractIds, Long derivedContractId, Long originRenegociationId, LocalDateTime createdAt, LocalDateTime updatedAt, String status) {}