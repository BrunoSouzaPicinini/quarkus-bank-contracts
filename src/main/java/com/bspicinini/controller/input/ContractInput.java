package com.bspicinini.controller.input;

import java.math.BigDecimal;
import java.util.List;

public record ContractInput(BigDecimal amount, Integer installments, Long customerId, List<Long> originContractIds) {
}