package com.bspicinini.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.bspicinini.config.MapStructConfig;
import com.bspicinini.controller.input.PaymentInput;
import com.bspicinini.repository.entity.Payment;
import com.bspicinini.service.dto.PaymentDto;

@Mapper(config = MapStructConfig.class)
public interface PaymentMapper {
    PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);

    Payment toEntity(PaymentInput input);
    PaymentDto toDto(Payment payment);
}