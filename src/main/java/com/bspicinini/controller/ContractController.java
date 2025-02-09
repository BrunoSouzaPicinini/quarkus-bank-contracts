package com.bspicinini.controller;

import com.bspicinini.controller.input.ContractInput;
import com.bspicinini.mapper.ContractMapper;
import com.bspicinini.service.ContractService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/contracts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ContractController {

    private ContractService contractService;
    private ContractMapper mapper;

    public ContractController(ContractService contractService, ContractMapper mapper) {
        this.contractService = contractService;
        this.mapper = mapper;
    }

    @GET
    public Response getAllContracts() {
        return Response.ok(contractService.findAllContracts().stream()
                .map(mapper::toResponse)
                .toList()).build();
    }

    @GET    
    @Path("/{id}")
    public Response getContractById(@PathParam("id") Long id) {
        return Response.ok(mapper.toResponse(contractService.findContractById(id))).build();
    }

    @POST
    public Response createContract(ContractInput contractInput) {
        var contractDto = contractService.createContract(contractInput);
        return Response.status(Response.Status.CREATED).entity(mapper.toResponse(contractDto)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteContract(@PathParam("id") Long id) {
        contractService.deleteContract(id);
        return Response.noContent().build();
    }
}