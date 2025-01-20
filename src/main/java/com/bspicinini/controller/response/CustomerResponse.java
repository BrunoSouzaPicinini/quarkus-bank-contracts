package com.bspicinini.controller.response;

import java.time.LocalDateTime;

public record CustomerResponse(Long id, String name, String email, LocalDateTime createdAt, LocalDateTime updatedAt) {}