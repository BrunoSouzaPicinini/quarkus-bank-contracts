package com.bspicinini.controller;

import com.bspicinini.controller.input.PaymentInput;
import com.bspicinini.mapper.PaymentMapper;
import com.bspicinini.service.PaymentService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/payments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PaymentController {

    private PaymentService paymentService;
    private PaymentMapper mapper;

    public PaymentController(PaymentService paymentService, PaymentMapper mapper) {
        this.paymentService = paymentService;
        this.mapper = mapper;
    }

    @GET
    public Response getAllPayments() {
        return Response.ok(paymentService.findAllPayments().stream()
                .map(mapper::toResponse)
                .toList()).build();
    }

    @GET
    @Path("/{id}")
    public Response getPaymentById(@PathParam("id") Long id) {
        return Response.ok(mapper.toResponse(paymentService.findPaymentById(id))).build();
    }

    @POST
    public Response createPayment(PaymentInput paymentInput) {
        var paymentDto = paymentService.createPayment(paymentInput);
        return Response.status(Response.Status.CREATED)
                .entity(mapper.toResponse(paymentDto)).build();
    }

}