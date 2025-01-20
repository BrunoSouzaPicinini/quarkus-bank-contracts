package com.bspicinini.controller;

import java.util.List;

import com.bspicinini.controller.input.CustomerInput;
import com.bspicinini.controller.response.CustomerResponse;
import com.bspicinini.mapper.CustomerMapper;
import com.bspicinini.service.CustomerService;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerController {

    private CustomerService customerService;
    private CustomerMapper mapper;

    public CustomerController(CustomerService customerService, CustomerMapper mapper) {
        this.customerService = customerService;
        this.mapper = mapper;
    }

    @GET
    public List<CustomerResponse> getAllCustomers() {
        return customerService.findAllCustomers().stream()
                .map(mapper::toResponse)
                .toList();
    }

    @GET
    @Path("/{id}")
    public CustomerResponse getCustomerById(@PathParam("id") Long id) {
        var customerDto = customerService.findCustomerById(id);
        return mapper.toResponse(customerDto);
    }

    @POST
    public Response createCustomer(CustomerInput customerInput) {
        var customerDto = customerService.createCustomer(customerInput);
        return Response.status(Response.Status.CREATED).entity(mapper.toResponse(customerDto)).build();
    }
}