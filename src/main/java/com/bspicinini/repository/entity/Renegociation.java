package com.bspicinini.repository.entity;

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

@Entity(name = "renegociations")
public class Renegociation {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "renegociation_seq")
    @SequenceGenerator(name = "renegociation_seq", sequenceName = "renegociation_sequence", allocationSize = 1)
    private Long id;
    @OneToMany(mappedBy = "originRenegociation")
    private List<Contract> contracts;
    @CreationTimestamp
    @Column(nullable = false, name = "created_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false, name = "updated_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime updatedAt;
    @Column(nullable = false, name = "expiration_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime expirationDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RenegociationStatus status;

    @ManyToOne
    @JoinColumn(nullable = false, name = "customer_offers_id")
    private CustomerOffer customerOffers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
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

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public RenegociationStatus getStatus() {
        return status;
    }

    public void setStatus(RenegociationStatus status) {
        this.status = status;
    }

    public CustomerOffer getCustomerOffers() {
        return customerOffers;
    }

    public void setCustomerOffers(CustomerOffer customerOffers) {
        this.customerOffers = customerOffers;
    }

}
