package ch.fhnw.eaf.rental.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import ch.fhnw.eaf.rental.model.Movie;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MovieRepositoryTest {

	@Autowired
	private MovieRepository repo;

	private int totalNumberOfMovies;

	@Before
	public void setUp() {
		totalNumberOfMovies = repo.findAll().size();
	}

	@Test
	public void testCount() {
		assertEquals("result returned by count is not correct:", totalNumberOfMovies, repo.count());
	}

	@Test
	public void testExists() {
		assertTrue("movie with id 5 should exist in repo", repo.existsById(5L));
		assertFalse("movie with id 55 should not exist in repo", repo.existsById(55L));
	}

	@Test
	public void testDeleteId1() {
		repo.deleteById(5L);
		assertEquals("number of movies is not orrect after deleting movie with id 5: ", totalNumberOfMovies - 1, repo.count());
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
		assertEquals("number of movies returned by findAll is not correct:", totalNumberOfMovies, list.size());
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
