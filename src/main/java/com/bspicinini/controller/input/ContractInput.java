package com.bspicinini.controller.input;

import java.math.BigDecimal;
import java.util.List;

public record ContractInput(BigDecimal amount, Integer numberOfInstallments, List<Long> originContractIds, Long customerOfferId, Long renegociationId) {
}