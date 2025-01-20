package com.bspicinini.controller;

import java.util.List;

import com.bspicinini.controller.input.PaymentInput;
import com.bspicinini.controller.response.PaymentResponse;
import com.bspicinini.service.PaymentService;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
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
    
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GET
    public List<PaymentResponse> getAllPayments() {
        return paymentService.findAllPayments().stream()
                .map(payment -> new PaymentResponse(payment.id(), payment.amount(), payment.paymentDate()))
                .toList();
    }

    @GET
    @Path("/{id}")
    public PaymentResponse getPaymentById(@PathParam("id") Long id) {
        var paymentDto = paymentService.findPaymentById(id);
        return new PaymentResponse(paymentDto.id(), paymentDto.amount(), paymentDto.paymentDate());
    }

    @POST
    public Response createPayment(PaymentInput paymentInput) {

        var paymentDto = paymentService.createPayment(paymentInput);
        return Response.status(Response.Status.CREATED).entity(new PaymentResponse(paymentDto.id(), paymentDto.amount(), paymentDto.paymentDate())).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletePayment(@PathParam("id") Long id) {
        paymentService.deletePayment(id);
        return Response.noContent().build();
    }
}