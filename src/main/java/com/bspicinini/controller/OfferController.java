package com.bspicinini.controller;

import com.bspicinini.controller.input.OfferInput;
import com.bspicinini.service.OfferService;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/offers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OfferController {

	private final OfferService offerService;

	public OfferController(OfferService offerService) {
		this.offerService = offerService;
	}

	@POST
	public void createOffer(OfferInput offerInput) {;
		offerService.createOffer(offerInput);
	}
}
