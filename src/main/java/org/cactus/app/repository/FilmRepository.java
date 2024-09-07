package org.cactus.app.repository;

import com.speedment.jpastreamer.application.JPAStreamer;
import com.speedment.jpastreamer.projection.Projection;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.cactus.app.model.Film;
import org.cactus.app.model.Film$;

import java.util.Optional;
import java.util.stream.Stream;

@ApplicationScoped
public class FilmRepository {

    @Inject
    JPAStreamer jpaStreamer;

    private static final int PAGE_SIZE = 10;

    public Optional<Film> findFilmById(short id) {
        return jpaStreamer.stream(Film.class)
                .filter(Film$.id.in(id))
                .findFirst();
    }

    public Stream<Film> pageFilms(long page, short size) {
        return jpaStreamer.stream(Projection.select(Film$.id, Film$.title, Film$.length))
                .filter(Film$.length.greaterThan(size))
                .sorted(Film$.title)
                .skip(page * PAGE_SIZE)
                .limit(PAGE_SIZE);
    }
}
