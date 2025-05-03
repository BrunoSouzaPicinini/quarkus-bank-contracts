package com.bspicinini.controller;

import com.bspicinini.controller.input.CustomerOfferInput;
import com.bspicinini.service.CustomerOfferService;

import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;

@Path("/customer-offers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerOfferController {

	private final CustomerOfferService customerOfferService;

	public CustomerOfferController(CustomerOfferService customerOfferService) {
		this.customerOfferService = customerOfferService;
	}

	@POST
	public void createCustomerOffer(CustomerOfferInput customerOfferInput) {
		customerOfferService.createCustomerOffer(customerOfferInput);
	}

}
