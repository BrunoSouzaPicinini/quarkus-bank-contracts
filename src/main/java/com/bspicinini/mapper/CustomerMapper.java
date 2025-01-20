package com.bspicinini.mapper;

import com.bspicinini.config.MapStructConfig;
import com.bspicinini.controller.input.CustomerInput;
import com.bspicinini.controller.response.CustomerResponse;
import com.bspicinini.repository.entity.Customer;
import com.bspicinini.service.dto.CustomerDto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(config = MapStructConfig.class)
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    Customer toEntity(CustomerInput input);
    CustomerDto toDto(Customer customer);
    CustomerResponse toResponse(CustomerDto dto);
}