package com.bspicinini.controller;

import java.util.List;

import com.bspicinini.controller.input.OriginContractInput;
import com.bspicinini.controller.response.OriginContractResponse;
import com.bspicinini.service.OriginContractService;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/origin-contracts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OriginContractController {

    private OriginContractService originContractService;

    public OriginContractController(OriginContractService originContractService) {
        this.originContractService = originContractService;
    }

    @GET
    public List<OriginContractResponse> getAllOriginContracts() {
        return originContractService.findAllOriginContracts().stream()
                .map(originContract -> new OriginContractResponse(originContract.id(), originContract.originName()))
                .toList();
    }

    @GET
    @Path("/{id}")
    public OriginContractResponse getOriginContractById(@PathParam("id") Long id) {
        var originContract = originContractService.findOriginContractById(id);
        return new OriginContractResponse(originContract.id(), originContract.originName());
    }

    @POST
    public Response createOriginContract(OriginContractInput originContractInput) {

        var originContractDto = originContractService.createOriginContract(originContractInput);
        return Response.status(Response.Status.CREATED).entity(new OriginContractResponse(originContractDto.id(), originContractDto.originName())).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteOriginContract(@PathParam("id") Long id) {
        originContractService.deleteOriginContract(id);
        return Response.noContent().build();
    }
}