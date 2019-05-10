package org.fuin.examples.jkmq.service;

import javax.ejb.EJBAccessException;
import javax.ejb.EJBException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Maps the EJB exceptions into a HTTP status.
 */
@Provider
public class EJBExceptionMapper implements ExceptionMapper<EJBException> {

    @Override
    public Response toResponse(final EJBException ex) {

        if (ex.getCause() instanceof PersonNotFoundException) {
            return Response.status(Status.NOT_FOUND).build();
        }

        if (ex instanceof EJBAccessException) {
            return Response.status(Status.UNAUTHORIZED)
                    .entity("<html><head><title>Error</title></head><body>Unauthorized</body></html>")
                    .type(MediaType.TEXT_HTML_TYPE).build();
        }

        return Response.status(Status.INTERNAL_SERVER_ERROR).entity(
                "<html><head><title>Error</title></head><body>Internal server error</body></html>")
                .type(MediaType.TEXT_HTML_TYPE).build();
    }

}
