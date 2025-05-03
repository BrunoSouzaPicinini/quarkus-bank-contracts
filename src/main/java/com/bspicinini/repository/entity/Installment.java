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

@Entity(name = "installments")
public class Installment {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "installment_seq")
    @SequenceGenerator(name = "installment_seq", sequenceName = "installment_sequence", allocationSize = 1)
    private Long id;
	@Column(nullable = false, name= "base_value")
	private BigDecimal baseValue;
	@Column(nullable = false, name = "installment_number")
	private Integer installmentNumber;
	@Column(name = "interest_value")
	private BigDecimal interestValue;
	@Column(nullable = false, name = "due_date", columnDefinition = "TIMESTAMP")
	private LocalDateTime dueDate;
	@Column(name = "payment_date", columnDefinition = "TIMESTAMP")
	private LocalDateTime paymentDate;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private InstallmentStatus status;
	@CreationTimestamp
	@Column(nullable = false, name = "created_at", columnDefinition = "TIMESTAMP")
	private LocalDateTime createdAt;
	@UpdateTimestamp
    @Column(nullable = false, name = "updated_at", columnDefinition = "TIMESTAMP")
	private LocalDateTime updatedAt;
	@ManyToOne
	@JoinColumn(nullable = false, name = "contract_id")
	private Contract contract;
	@OneToMany(mappedBy = "installment")
	private List<Payment> payments;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public BigDecimal getBaseValue() {
		return baseValue;
	}
	public void setBaseValue(BigDecimal baseValue) {
		this.baseValue = baseValue;
	}
	public Integer getInstallmentNumber() {
		return installmentNumber;
	}
	public void setInstallmentNumber(Integer installmentNumber) {
		this.installmentNumber = installmentNumber;
	}
	public BigDecimal getInterestValue() {
		return interestValue;
	}
	public void setInterestValue(BigDecimal interestValue) {
		this.interestValue = interestValue;
	}
	public LocalDateTime getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDateTime dueDate) {
		this.dueDate = dueDate;
	}
	public LocalDateTime getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(LocalDateTime paymentDate) {
		this.paymentDate = paymentDate;
	}
	public InstallmentStatus getStatus() {
		return status;
	}
	public void setStatus(InstallmentStatus status) {
		this.status = status;
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
	public Contract getContract() {
		return contract;
	}
	public void setContract(Contract contract) {
		this.contract = contract;
	}
	public List<Payment> getPayments() {
		return payments;
	}
	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}

	
}
