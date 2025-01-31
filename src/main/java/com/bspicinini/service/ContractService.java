package com.bspicinini.service;

import com.bspicinini.controller.input.ContractInput;
import com.bspicinini.mapper.ContractMapper;
import com.bspicinini.repository.ContractRepository;
import com.bspicinini.repository.entity.Contract;
import com.bspicinini.repository.entity.ContractStatusEnum;
import com.bspicinini.repository.entity.Customer;
import com.bspicinini.service.dto.ContractDto;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

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

    @Transactional
    public ContractDto createContract(ContractInput contractInput) {
        Contract contract = ContractMapper.INSTANCE.toEntity(contractInput);
        var customer = new Customer();
        customer.setId(contractInput.customerId());
        contract.setCustomer(customer);

        if(contractInput.originContractIds() != null){
            contract.setOriginContracts(contractInput.originContractIds().stream()
                    .map(id -> {
                        var originContract = new Contract();
                        originContract.setId(id);
                        return originContract;
                    })
                    .collect(Collectors.toList()));
        }

        contract.setStatus(ContractStatusEnum.ACTIVE);
        contractRepository.persist(contract);

        if(contract.getOriginContracts() != null && !contract.getOriginContracts().isEmpty()) {
           List<Contract> originContracts = contractRepository.findByIdIn(contract.getOriginContracts().stream().map(Contract::getId).toList());
           originContracts.forEach(originContract -> {
               originContract.setStatus(ContractStatusEnum.RENEGOTIATED);
               originContract.setDerivedContract(contract);
               contractRepository.persist(originContract);
           });
        }
        
        return ContractMapper.INSTANCE.toDto(contract);
    }

    @Transactional
    public void deleteContract(Long id) {
        var contract = contractRepository.findById(id);
        contract.setStatus(ContractStatusEnum.CANCELED);
        contractRepository.persist(contract);
    }
}