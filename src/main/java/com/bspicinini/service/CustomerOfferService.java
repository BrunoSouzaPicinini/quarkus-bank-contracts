package com.bspicinini.service;

import com.bspicinini.controller.input.CustomerOfferInput;
import com.bspicinini.repository.CustomerOfferRepository;
import com.bspicinini.repository.entity.Customer;
import com.bspicinini.repository.entity.CustomerOffer;
import com.bspicinini.repository.entity.Offer;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CustomerOfferService {

	private final CustomerOfferRepository customerOffersRepository;

	public CustomerOfferService(CustomerOfferRepository customerOfferRepository) {
		this.customerOffersRepository = customerOfferRepository;
	}

	@Transactional
	public void createCustomerOffer(CustomerOfferInput customerOfferInput) {
		CustomerOffer newCustomerOffer = new CustomerOffer();
		var customer = new Customer();
		customer.setId(customerOfferInput.customerId());
		newCustomerOffer.setCustomer(customer);
		var offer = new Offer();
		offer.setId(customerOfferInput.offerId());
		newCustomerOffer.setOffer(offer);
		
		customerOffersRepository.persist(newCustomerOffer);
	}

}
