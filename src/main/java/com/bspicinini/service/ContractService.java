package com.bspicinini.service;

import com.bspicinini.controller.input.ContractInput;
import com.bspicinini.mapper.ContractMapper;
import com.bspicinini.repository.ContractRepository;
import com.bspicinini.repository.entity.Contract;
import com.bspicinini.service.dto.ContractDto;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ContractService {

    private final ContractRepository contractRepository;

    public ContractService(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    public List<ContractDto> findAllContracts() {
        return contractRepository.listAll().stream()
                .map(ContractMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    public ContractDto findContractById(Long id) {
        Contract contract = contractRepository.findById(id);
        return ContractMapper.INSTANCE.toDto(contract);
    }

    public ContractDto createContract(ContractInput contractInput) {
        Contract contract = ContractMapper.INSTANCE.toEntity(contractInput);
        contractRepository.persist(contract);
        return ContractMapper.INSTANCE.toDto(contract);
    }

    public void deleteContract(Long id) {
        contractRepository.deleteById(id);
    }
}