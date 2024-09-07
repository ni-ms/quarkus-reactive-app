package org.cactus;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("/")
public class FilmResource {
    @GET
    @Path("/films")
    @Produces("text/plain")
    public String getFilms() {
        return "Films";
    }
}
