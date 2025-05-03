package com.bspicinini.repository.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

@Entity(name = "offers")
public class Offer {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "offer_seq")
    @SequenceGenerator(name = "offer_seq", sequenceName = "offer_sequence", allocationSize = 1)
    private Long id;
	@Column(nullable = false, name = "interest_rate")
	private BigDecimal interestRate;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private OfferType type;
	@Column(nullable = false, name = "days_overdue")
	private Integer daysOverdue;
	@Column(nullable = false, name = "max_installments")
	private Integer maxInstallments;
	@Column(nullable = false, name = "min_installments")
	private Integer minInstallments;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private OfferScope scope;
	@Column(columnDefinition = "TIMESTAMP", name = "start_date")
	private LocalDateTime startDate;
	@Column(columnDefinition = "TIMESTAMP", name = "expiration_date")
	private LocalDateTime expirationDate;
	@OneToMany(mappedBy = "offer")
    private List<CustomerOffer> customerOffers;

	@Column(name = "min_down_payment_percentage")
	private BigDecimal minDownPaymentPercentage;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public BigDecimal getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(BigDecimal interestRate) {
		this.interestRate = interestRate;
	}
	public OfferType getType() {
		return type;
	}
	public void setType(OfferType offerType) {
		this.type = offerType;
	}
	public Integer getDaysOverdue() {
		return daysOverdue;
	}
	public void setDaysOverdue(Integer daysOverdue) {
		this.daysOverdue = daysOverdue;
	}
	public Integer getMaxInstallments() {
		return maxInstallments;
	}
	public void setMaxInstallments(Integer maxInstallments) {
		this.maxInstallments = maxInstallments;
	}
	public Integer getMinInstallments() {
		return minInstallments;
	}
	public void setMinInstallments(Integer minInstallments) {
		this.minInstallments = minInstallments;
	}
	public OfferScope getScope() {
		return scope;
	}
	public void setScope(OfferScope scope) {
		this.scope = scope;
	}
	public LocalDateTime getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}
	public LocalDateTime getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(LocalDateTime expirationDate) {
		this.expirationDate = expirationDate;
	}
	public List<CustomerOffer> getCustomerOffers() {
		return customerOffers;
	}
	public void setCustomerOffers(List<CustomerOffer> customerOffers) {
		this.customerOffers = customerOffers;
	}
	public BigDecimal getMinDownPaymentPercentage() {
		return minDownPaymentPercentage;
	}
	public void setMinDownPaymentPercentage(BigDecimal minDownPaymentPercentage) {
		this.minDownPaymentPercentage = minDownPaymentPercentage;
	}
	
}
