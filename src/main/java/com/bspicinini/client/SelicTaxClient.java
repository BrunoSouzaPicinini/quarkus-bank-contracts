package com.bspicinini.client;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@RegisterRestClient(configKey = "selic-api")
@Path("/dados/serie")
public interface SelicTaxClient {

    @GET
    @Produces(MediaType.APPLICATION_JSON)   
    @Path("/bcdata.sgs.4189/dados") 
    List<SelicResponse> getSelicAnualTaxByBetweenDates( @QueryParam ("formato") String format, @QueryParam("dataInicial") String startDate, @QueryParam("dataFinal") String endDate);
    
    default List<SelicResponse> getSelicAnualTaxByBetweenDates(LocalDate startDate, LocalDate endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return getSelicAnualTaxByBetweenDates("json",  startDate.format(formatter), endDate.format(formatter));
    }


}