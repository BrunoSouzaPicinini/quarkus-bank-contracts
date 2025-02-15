package com.bspicinini.exception;

import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

import jakarta.ws.rs.core.Response;

public class GlobalErrorHandler {
	@ServerExceptionMapper(BusinessException.class)
	public Response handleCustomRuntimeException(BusinessException exception) {
		return Response.serverError()
				.header("Content-Type", "application/json")
				.status(422)
				.entity(new CustomError(420, exception.getMessage()))
				.build();
	}

	public record CustomError (int code, String message) {}		
}
