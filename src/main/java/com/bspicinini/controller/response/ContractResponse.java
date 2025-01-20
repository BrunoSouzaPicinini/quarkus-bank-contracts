package com.bspicinini.controller.response;

import java.math.BigDecimal;

public record ContractResponse(Long id, String contractNumber, BigDecimal amount) {}