package com.bspicinini.service;

import com.bspicinini.controller.input.OriginContractInput;
import com.bspicinini.mapper.OriginContractMapper;
import com.bspicinini.repository.OriginContractRepository;
import com.bspicinini.repository.entity.OriginContract;
import com.bspicinini.service.dto.OriginContractDto;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class OriginContractService {

    private final OriginContractRepository originContractRepository;

    public OriginContractService(OriginContractRepository originContractRepository) {
        this.originContractRepository = originContractRepository;
    }

    public List<OriginContractDto> findAllOriginContracts() {
        return originContractRepository.listAll().stream()
                .map(OriginContractMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    public OriginContractDto findOriginContractById(Long id) {
        OriginContract originContract = originContractRepository.findById(id);
        return OriginContractMapper.INSTANCE.toDto(originContract);
    }

    public OriginContractDto createOriginContract(OriginContractInput originContractInput) {
        OriginContract originContract = OriginContractMapper.INSTANCE.toEntity(originContractInput);
        originContractRepository.persist(originContract);
        return OriginContractMapper.INSTANCE.toDto(originContract);
    }

    public void deleteOriginContract(Long id) {
        originContractRepository.deleteById(id);
    }
}