package com.bspicinini.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity(name = "customer_offers")
public class CustomerOffer {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_offers_seq")
    @SequenceGenerator(name = "customer_offers_seq", sequenceName = "customer_offers_sequence", allocationSize = 1)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "customer_id") 
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "offer_id") 
    private Offer offer;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public Offer getOffer() {
        return offer;
    }
    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    
}
