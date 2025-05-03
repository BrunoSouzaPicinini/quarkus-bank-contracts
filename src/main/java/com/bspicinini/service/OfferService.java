package com.bspicinini.service;

import com.bspicinini.controller.input.OfferInput;
import com.bspicinini.repository.OfferRepository;
import com.bspicinini.repository.entity.Offer;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class OfferService {

	private final OfferRepository offerRepository;

	public OfferService(OfferRepository offerRepository) {
		this.offerRepository = offerRepository;
	}

	@Transactional
	public void createOffer(OfferInput offerInput) {
		var offer = new Offer();
		offer.setInterestRate( offerInput.interestRate());
		offer.setType( offerInput.type());
		offer.setDaysOverdue( offerInput.daysOverdue());
		offer.setMaxInstallments( offerInput.maxInstallments());
		offer.setMinInstallments( offerInput.minInstallments());
		offer.setScope( offerInput.scope());
		offer.setStartDate( offerInput.startDate());
		offer.setExpirationDate( offerInput.expirationDate());
		
		offerRepository.persist(offer);
	}

}
