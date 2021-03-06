package com.highway2urhell.rest.provider;

import com.highway2urhell.rest.exception.ASRuntimeException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ASExceptionMapper implements ExceptionMapper<ASRuntimeException> {
	@Override
    public Response toResponse(ASRuntimeException exception) {
        return Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).type("text/plain").build();
    }
}