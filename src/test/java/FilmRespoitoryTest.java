import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.freecodecamp.app.model.FilmEntity;
import org.freecodecamp.app.model.repository.FilmRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
public class FilmRespoitoryTest {
    @Inject
    FilmRepository filmRepository;

    @Test
    public void test(){
        Optional<FilmEntity> optionalFilm = filmRepository.getFilm((short) 5);
        assertTrue(optionalFilm.isPresent());
        assertEquals("AFRICAN EGG", optionalFilm.get().getTitle());
    }
}
