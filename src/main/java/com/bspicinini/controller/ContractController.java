package com.bspicinini.controller;

import java.util.List;

import com.bspicinini.controller.input.ContractInput;
import com.bspicinini.controller.response.ContractResponse;
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
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<ContractResponse> getAllContracts() {
        return contractService.findAllContracts().stream()
                .map(mapper::toResponse)
                .toList();
    }

    @GET    
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ContractResponse getContractById(@PathParam("id") Long id) {
        var contractDto = contractService.findContractById(id);
        return mapper.toResponse(contractDto);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
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