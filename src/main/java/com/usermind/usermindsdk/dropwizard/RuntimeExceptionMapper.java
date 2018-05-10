package com.usermind.usermindsdk.dropwizard;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class RuntimeExceptionMapper implements ExceptionMapper<RuntimeException> {
    public Response toResponse(RuntimeException exception) {
        return Response.status(500)
                .entity(exception.getMessage())
                .type(MediaType.TEXT_PLAIN)
                .build();
    }
}

