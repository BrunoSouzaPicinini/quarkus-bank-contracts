package com.bspicinini.controller;

import java.util.List;

import com.bspicinini.service.CustomerService;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
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

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GET
    public List<CustomerResponse> getAllCustomers() {
        return customerService.findAllCustomers().stream()
                .map(customer -> new CustomerResponse(customer.id(), customer.name(), customer.email()))
                .toList();
    }

    @GET
    @Path("/{id}")
    public CustomerResponse getCustomerById(@PathParam("id") Long id) {
        var customerDto = customerService.findCustomerById(id);
        return new CustomerResponse(customerDto.id(), customerDto.name(), customerDto.email());
    }

    @POST
    public Response createCustomer(CustomerInput customerInput) {
        var customerDto = customerService.createCustomer(customerInput);
        return Response.status(Response.Status.CREATED).entity(new CustomerResponse(customerDto.id(), customerDto.name(), customerDto.email())).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCustomer(@PathParam("id") Long id) {
        customerService.deleteCustomer(id);
        return Response.noContent().build();
    }

    public record CustomerResponse(Long id, String name, String email) {}
    public record CustomerInput(String name, String email) {}
}