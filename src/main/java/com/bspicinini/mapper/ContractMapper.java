package com.bspicinini.mapper;

import com.bspicinini.config.MapStructConfig;
import com.bspicinini.controller.input.ContractInput;
import com.bspicinini.controller.response.ContractResponse;
import com.bspicinini.repository.entity.Contract;
import com.bspicinini.service.dto.ContractDto;


import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(config = MapStructConfig.class)
public interface ContractMapper {
    ContractMapper INSTANCE = Mappers.getMapper(ContractMapper.class);

    Contract toEntity(ContractInput input);
    @Mapping(target = "customerId", source="customer.id")
    @Mapping(target = "originContractIds", source="originContracts", qualifiedByName = "getOriginContractId")
    @Mapping(target = "derivedContractId", source = "derivedContract.id")
    ContractDto toDto(Contract contract);
    ContractResponse toResponse(ContractDto dto);

    @Named("getOriginContractId") 
    default List<Long> getOriginContractId(List<Contract> originContracts) {
        return originContracts.stream().map(Contract::getId).toList();
    }   
}