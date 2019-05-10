package org.fuin.examples.jkmq.service;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.opentracing.Traced;

@Traced
@Stateless
@Path("/admin")
public class AdminResource {

    @GET
    @Path("/hello")
    @Produces({ MediaType.TEXT_HTML })
    @RolesAllowed(Constants.ROLE_ADMIN)
    public String getHello() {
        return "<html><head><title>hello, admin</title></head><body><h1>hello, admin</h1></body></html>";
    }

}
