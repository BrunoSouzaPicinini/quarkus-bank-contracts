package com.bspicinini.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.bspicinini.config.MapStructConfig;
import com.bspicinini.controller.input.OriginContractInput;
import com.bspicinini.repository.entity.OriginContract;
import com.bspicinini.service.dto.OriginContractDto;

@Mapper(config = MapStructConfig.class)
public interface OriginContractMapper {
    OriginContractMapper INSTANCE = Mappers.getMapper(OriginContractMapper.class);

    OriginContract toEntity(OriginContractInput input);

    OriginContractDto toDto(OriginContract originContract);
}