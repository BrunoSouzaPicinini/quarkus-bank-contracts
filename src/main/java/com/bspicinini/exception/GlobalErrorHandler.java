package com.bspicinini.exception;


import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;

public class GlobalErrorHandler {
	private static final Logger logger = Logger.getLogger(GlobalErrorHandler.class);

	@ServerExceptionMapper(BusinessException.class)
	public Response handleCustomRuntimeException(BusinessException exception) {
		return Response.serverError()
				.header("Content-Type", "application/json")
				.status(422)
				.entity(new CustomError(420, exception.getMessage()))
				.build();
	}

	@ServerExceptionMapper(Exception.class)
	public Response handleRuntimeException(Exception exception) {
		logger.error("Error: ", exception);

		return Response.serverError()
				.header("Content-Type", "application/json")
				.status(500)
				.entity(new CustomError(500, "Server Error"))
				.build();
	}

	public record CustomError (int code, String message) {}		
}
