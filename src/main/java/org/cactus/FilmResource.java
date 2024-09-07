package org.cactus;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import org.cactus.app.model.Film;
import org.cactus.app.repository.FilmRepository;

import java.util.Optional;

@Path("/")
public class FilmResource {
    @Inject
    FilmRepository filmRepository;

    @GET
    @Path("/films")
    @Produces("text/plain")
    public String getFilms() {
        return "Films";
    }


    @GET
    @Path("/film/{filmId}")
    @Produces("text/plain")
    public String getFilmById(@PathParam("filmId") short filmId) {
        Optional<Film> film = filmRepository.findFilmById(filmId);
        return film.isPresent() ? film.get().getTitle() : "Film not found";
    }

    @GET
    @Path("/films/{page}/{size}")
    @Produces("text/plain")
    public String getFilmsByPage(@PathParam("page") long page, @PathParam("size") short size) {
        return filmRepository.pageFilms(page, size)
                .map(film -> film.getTitle() + " (" + film.getLength() + " minutes)")
                .reduce("", (a, b) -> a + "\n" + b);
    }
}
