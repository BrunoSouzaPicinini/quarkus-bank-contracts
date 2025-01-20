package com.bspicinini.controller;

import java.util.List;

import com.bspicinini.controller.input.ContractInput;
import com.bspicinini.controller.response.ContractResponse;
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

    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @GET
    public List<ContractResponse> getAllContracts() {
        return contractService.findAllContracts().stream()
                .map(contract -> new ContractResponse(contract.id(), contract.contractNumber(), contract.amount()))
                .toList();
    }

    @GET    
    @Path("/{id}")
    public ContractResponse getContractById(@PathParam("id") Long id) {
        var contractDto = contractService.findContractById(id);
        return new ContractResponse(contractDto.id(), contractDto.contractNumber(), contractDto.amount());
    }

    @POST
    public Response createContract(ContractInput contractInput) {
        ContractInput input = new ContractInput(contractInput.contractNumber(), contractInput.amount());
        var contractDto = contractService.createContract(input);
        return Response.status(Response.Status.CREATED).entity(new ContractResponse(contractDto.id(), contractDto.contractNumber(), contractDto.amount())).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteContract(@PathParam("id") Long id) {
        contractService.deleteContract(id);
        return Response.noContent().build();
    }
}