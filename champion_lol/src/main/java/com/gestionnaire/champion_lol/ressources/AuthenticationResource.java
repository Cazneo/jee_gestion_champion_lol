package com.gestionnaire.champion_lol.ressources;

import com.gestionnaire.champion_lol.model.LoginRequest;
import com.gestionnaire.champion_lol.services.AuthenticationService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("auth")
public class AuthenticationResource {
    private AuthenticationService authenticationService = new AuthenticationService();

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginRequest loginRequest) {
        String token = authenticationService.authenticate(loginRequest);

        if (token != null) {
            return Response.ok().entity(token).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @POST
    @Path("/logout")
    public Response logout() {
        return Response.ok().build();
    }
}
