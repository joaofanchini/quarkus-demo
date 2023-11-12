package org.freecodecamp.app.model;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.freecodecamp.app.model.repository.FilmRepository;

import java.util.Optional;
import java.util.stream.Collectors;

@Path("/")
public class FilmResource {

    @Inject
    FilmRepository filmRepository;

    @GET
    @Path("/helloWorld")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello World";
    }

    @GET
    @Path("/helloWorld2")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello2() {
        return "Hello World 2";
    }

    @GET
    @Path("/film/{filmId}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getFilm(short filmId) {
        Optional<FilmEntity> optionalFilmEntity = filmRepository.getFilm(filmId);
        return optionalFilmEntity.isPresent() ? optionalFilmEntity.get().getTitle() : "No film was founded";
    }

    @GET
    @Path("/films/{minLength}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getFilms(short minLength) {
        return filmRepository.getFilms(minLength)
                .map( filmEntity -> String.format("%s (%s)", filmEntity.getTitle(), filmEntity.getDescription()))
                .collect(Collectors.joining("\n"));
    }


    @GET
    @Path("/pagedFilms/{page}/{minLength}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getFilm(short page, short minLength) {
        return filmRepository.paged(page, minLength)
                .map( filmEntity -> String.format("%s (%d min)", filmEntity.getTitle(), filmEntity.getLength()))
                .collect(Collectors.joining("\n"));
    }

    @GET
    @Path("/update/{minLength}/{description}")
    public void updateFilm(short minLength, String description){
        filmRepository.updateDescription(minLength, description);
    }

    @GET
    @Path("/actors/{startsWith}/{minLength}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getFilm(String startsWith, short minLength) {
        return filmRepository.actors(startsWith, minLength)
                .map( filmEntity -> String.format("%s (%d min): %s",
                        filmEntity.getTitle(),
                        filmEntity.getLength(),
                        filmEntity.getActors().stream().map(actor -> String.format("%s %s", actor.getFirstName(), actor.getLastName()))
                                .collect(Collectors.joining("|"))
                ))
                .collect(Collectors.joining("\n"));
    }
}
