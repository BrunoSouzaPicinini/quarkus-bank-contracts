package com.bspicinini.service.dto;

import java.time.LocalDateTime;

public record CustomerDto(Long id, String name, String email, LocalDateTime createdAt, LocalDateTime updatedAt) {}