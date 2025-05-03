package com.bspicinini.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.bspicinini.config.MapStructConfig;
import com.bspicinini.controller.input.PaymentInput;
import com.bspicinini.controller.response.PaymentResponse;
import com.bspicinini.repository.entity.Payment;
import com.bspicinini.service.dto.PaymentDto;

@Mapper(config = MapStructConfig.class)
public interface PaymentMapper {
    PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);

    Payment toEntity(PaymentInput input);
    @Mapping(target = "installmentId", source = "installment.id")
    PaymentDto toDto(Payment payment);
    PaymentResponse toResponse(PaymentDto dto);
}