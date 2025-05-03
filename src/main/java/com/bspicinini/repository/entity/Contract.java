package com.bspicinini.repository.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

@Entity(name = "contracts")
public class Contract {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contract_seq")
    @SequenceGenerator(name = "contract_seq", sequenceName = "contract_sequence", allocationSize = 1)
    private Long id;
    @Column(nullable = false)
    private BigDecimal amount;
    @Column(nullable = false, name="number_of_installments")
    private Integer numberOfInstallments;

    @OneToMany(mappedBy = "derivedContract")
    private List<Contract> originContracts;

    @ManyToOne
    @JoinColumn(name = "derived_contract_id") 
    private Contract derivedContract;

    @ManyToOne
    @JoinColumn(name = "origin_renegociation_id")
    private Renegociation originRenegociation;

    @OneToMany(mappedBy = "contract")
    private List<Installment> installments;

    @CreationTimestamp
    @Column(nullable = false, name = "created_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false, name = "updated_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ContractStatusEnum status;

	@ManyToOne
    @JoinColumn(nullable = false, name = "customer_offers_id")
    private CustomerOffer customerOffers;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Integer getNumberOfInstallments() {
		return numberOfInstallments;
	}

	public void setNumberOfInstallments(Integer numberOfInstallments) {
		this.numberOfInstallments = numberOfInstallments;
	}

	public List<Contract> getOriginContracts() {
		return originContracts;
	}

	public void setOriginContracts(List<Contract> originContracts) {
		this.originContracts = originContracts;
	}

	public Contract getDerivedContract() {
		return derivedContract;
	}

	public void setDerivedContract(Contract derivedContract) {
		this.derivedContract = derivedContract;
	}

	public Renegociation getOriginRenegociation() {
		return originRenegociation;
	}

	public void setOriginRenegociation(Renegociation renegociation) {
		this.originRenegociation = renegociation;
	}

	public List<Installment> getInstallments() {
		return installments;
	}

	public void setInstallments(List<Installment> installments) {
		this.installments = installments;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public ContractStatusEnum getStatus() {
		return status;
	}

	public void setStatus(ContractStatusEnum status) {
		this.status = status;
	}

	public CustomerOffer getCustomerOffers() {
		return customerOffers;
	}

	public void setCustomerOffers(CustomerOffer customerOffers) {
		this.customerOffers = customerOffers;
	}
    
	public static Contract withId(Long id) {
		var contract = new Contract();
		contract.setId(id);
		return contract;
	}
}
