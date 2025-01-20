package com.bspicinini.mapper;

import com.bspicinini.config.MapStructConfig;
import com.bspicinini.controller.input.ContractInput;
import com.bspicinini.repository.entity.Contract;
import com.bspicinini.service.dto.ContractDto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(config = MapStructConfig.class)
public interface ContractMapper {
    ContractMapper INSTANCE = Mappers.getMapper(ContractMapper.class);

    Contract toEntity(ContractInput input);
    ContractDto toDto(Contract contract);
}