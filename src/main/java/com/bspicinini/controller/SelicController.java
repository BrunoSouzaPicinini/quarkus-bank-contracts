package com.bspicinini.controller;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.bspicinini.client.SelicTaxClient;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/selic")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SelicController {
	
	@Inject
	@RestClient
	private SelicTaxClient selicTaxClient;
	
	@GET
	public Response getSelicAnualTaxToday(){
		var now = java.time.LocalDate.now();
		return Response.ok(selicTaxClient.getSelicAnualTaxByBetweenDates(now, now)).build();
	}
}
