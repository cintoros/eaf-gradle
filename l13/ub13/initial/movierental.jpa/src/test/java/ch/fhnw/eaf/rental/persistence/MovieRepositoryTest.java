package ch.fhnw.eaf.rental.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import ch.fhnw.eaf.rental.model.Movie;

@SpringBootTest
@Transactional
public class MovieRepositoryTest {

    @Autowired
    private MovieRepository repo;

    private int totalNumberOfMovies;

    @BeforeEach
    public void setUp() {
        totalNumberOfMovies = repo.findAll().size();
    }

    @Test
    public void testCount() {
        assertEquals(totalNumberOfMovies, repo.count(), "result returned by count is not correct:");
    }

    @Test
    public void testExists() {
        assertTrue(repo.existsById(5L), "movie with id 5 should exist in repo");
        assertFalse(repo.existsById(55L), "movie with id 55 should not exist in repo");
    }

    @Test
    public void testDeleteId1() {
        repo.deleteById(5L);
        assertEquals(totalNumberOfMovies - 1, repo.count(),
                "number of movies is not orrect after deleting movie with id 5: ");
    }

    @Test
    public void testDeleteId2() {
        try {
            repo.deleteById(1111L);
            fail("expected an exception when deleting a non-existing entity");
        } catch (Exception e) {
            // OK
        }
    }

    @Test
    public void testDelete() {
        Movie m1 = repo.findById(1L).get();
        Movie m2 = new Movie(m1.getTitle(), m1.getReleaseDate(), m1.getPriceCategory());
        m2.setId(m1.getId());
        m2.setRented(m1.isRented());
        repo.delete(m2);
    }

    @Test
    public void testFindAll() {
        List<Movie> list = repo.findAll();
        assertEquals(totalNumberOfMovies, list.size(), "number of movies returned by findAll is not correct:");
        assertTrue(list.contains(repo.findById(1L).get()));
        assertTrue(list.contains(repo.findById(2L).get()));
        assertTrue(list.contains(repo.findById(3L).get()));
        assertTrue(list.contains(repo.findById(4L).get()));
        assertTrue(list.contains(repo.findById(5L).get()));
    }

    @Test
    public void testFindNonExistentEntity() {
        Optional<Movie> m = repo.findById(-1L);
        assertFalse(m.isPresent());
    }
}
