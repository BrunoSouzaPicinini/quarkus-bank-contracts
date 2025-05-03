package com.bspicinini.service;

import com.aayushatharva.brotli4j.common.annotations.Local;
import com.bspicinini.controller.input.ContractInput;
import com.bspicinini.exception.BusinessException;
import com.bspicinini.mapper.ContractMapper;
import com.bspicinini.repository.ContractRepository;
import com.bspicinini.repository.CustomerOfferRepository;
import com.bspicinini.repository.RenegociationRepository;
import com.bspicinini.repository.entity.Contract;
import com.bspicinini.repository.entity.ContractStatusEnum;
import com.bspicinini.repository.entity.CustomerOffer;
import com.bspicinini.repository.entity.Installment;
import com.bspicinini.repository.entity.InstallmentStatus;
import com.bspicinini.repository.entity.Payment;
import com.bspicinini.service.dto.ContractDto;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@ApplicationScoped
public class ContractService {

    private final ContractRepository contractRepository;
    private final CustomerOfferRepository customerOffersRepository;
    private final RenegociationRepository renegociationRepository;

    public ContractService(ContractRepository contractRepository, CustomerOfferRepository customerOffersRepository,
            RenegociationRepository renegociationRepository) {
        this.contractRepository = contractRepository;
        this.customerOffersRepository = customerOffersRepository;
        this.renegociationRepository = renegociationRepository;
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

        var renegociation = renegociationRepository.findById(contractInput.renegociationId());
        if (renegociation != null) {
            contract.setOriginRenegociation(renegociation);
        }
        var customerOffer = customerOffersRepository.findById(contractInput.customerOfferId());
        if (customerOffer == null) {
            throw new BusinessException("Customer offer not found");
        }
        contract.setCustomerOffers(customerOffer);

        generateInstallments(contract, customerOffer);

        if (contractInput.originContractIds() != null) {
            contract.setOriginContracts(contractInput.originContractIds().stream()
                    .map(Contract::withId).toList());
        }

        contract.setStatus(ContractStatusEnum.ACTIVE);
        contractRepository.persist(contract);

        if (contract.getOriginContracts() != null && !contract.getOriginContracts().isEmpty()) {
            List<Contract> originContracts = contractRepository
                    .findByIdIn(contract.getOriginContracts().stream().map(Contract::getId).toList());
            originContracts.forEach(originContract -> {
                originContract.setStatus(ContractStatusEnum.RENEGOTIATED);
                originContract.setDerivedContract(contract);
                contractRepository.persist(originContract);
            });
        }

        return ContractMapper.INSTANCE.toDto(contract);
    }


    private void generateInstallments(Contract contract, BigDecimal minDownPaymentPercentage) {
        List<Installment> installments = new ArrayList<>();
        Integer installmentNumberCounter = 0;
        BigDecimal minDownPayment = BigDecimal.ZERO;

        if(minDownPaymentPercentage.compareTo(BigDecimal.ZERO) == 1){
            installmentNumberCounter ++;
            Installment downPaymentInstallment = new Installment();
            minDownPayment = minDownPaymentPercentage.multiply(contract.getAmount()).divide(new BigDecimal(100))
            downPaymentInstallment.setBaseValue(minDownPayment);
	        downPaymentInstallment.setInstallmentNumber(installmentNumberCounter);
	        downPaymentInstallment.setDueDate(LocalDateTime.now().plusDays(4));
	        downPaymentInstallment.setStatus(InstallmentStatus.PENDING);	
            installments.add(downPaymentInstallment);
        }
        if(minDownPaymentPercentage.compareTo(new BigDecimal(100)) < 0) {
            Integer remainingInstallments = contract.getNumberOfInstallments() - installmentNumberCounter;
            BigDecimal remainingEachInstallmentValue = (contract.getAmount().subtract(minDownPayment)).divide(new BigDecimal(remainingInstallments),2, RoundingMode.HALF_EVEN);
            var nextDuePlusMonths = 0L;
            LocalDateTime dueDate = LocalDateTime.now();
            for (int i = 0; i < remainingInstallments; i++) {
                installmentNumberCounter++;
                nextDuePlusMonths++;
                dueDate.plusMonths(nextDuePlusMonths);
                Installment installment = new Installment();
                installment.setBaseValue(remainingEachInstallmentValue);
                installment.setInstallmentNumber(installmentNumberCounter);
                installment.setDueDate(dueDate);
                installment.setStatus(InstallmentStatus.PENDING);
                installments.add(installment);
            }
        }

        contract.setInstallments(installments);
    }

    @Transactional
    public void deleteContract(Long id) {
        var contract = contractRepository.findById(id);
        contract.setStatus(ContractStatusEnum.CANCELED);
        contractRepository.persist(contract);
    }
}