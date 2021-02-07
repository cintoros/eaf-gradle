package ch.fhnw.eaf.rental.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import ch.fhnw.eaf.rental.model.Rental;
import ch.fhnw.eaf.rental.model.User;

@SpringBootTest
@Transactional
public class UserRepositoryTest {

	@Autowired
	private UserRepository repo;

	private long totalNumberOfUsers;

	@BeforeEach
	public void setUp() {
		totalNumberOfUsers = repo.count();
	}

	@Test
	public void testCount() {
		assertEquals(totalNumberOfUsers, repo.count(), "expected 4 users in the user repo");
	}

	@Test
	public void testExists() {
		assertTrue(repo.existsById(4L), "user with id 4 should exist in repo");
		assertFalse(repo.existsById(44L), "user with id 44 should not exist in repo");
	}

	@Test
	public void testDeleteId1() {
		repo.deleteById(4L);
		assertEquals(totalNumberOfUsers - 1, repo.count(), "expected 3 users after deleting movie with id 4");
	}

	@Test
	public void testDeleteId2() {
		try {
			repo.deleteById(11L);
			fail("expected an exception when deleting a non-existing entity");
		} catch (Exception e) {
			// OK
		}
	}

	@Test
	public void testDelete() {
		User u1 = repo.findById(1L).get();
		User u2 = new User(u1.getLastName(), u1.getFirstName());
		u2.setEmail(u1.getEmail());
		u2.setRentals(u1.getRentals());
		u2.setId(u1.getId());
		repo.delete(u2);
	}

	@Test
	public void testFindAll() {
		List<User> list = repo.findAll();
		assertEquals(4, list.size());
		assertTrue(list.contains(repo.findById(1L).get()));
		assertTrue(list.contains(repo.findById(2L).get()));
		assertTrue(list.contains(repo.findById(3L).get()));
		assertTrue(list.contains(repo.findById(4L).get()));
	}

	@Test
	public void testFindNonExistentEntity() {
		Optional<User> m = repo.findById(-1L);
		assertFalse(m.isPresent());
	}

	@Test
	public void testFindOne() {
		Optional<User> opt = repo.findById(1L);
		assertTrue(opt.isPresent());
		User u = opt.get();
		assertEquals("Keller", u.getLastName());
		assertEquals(2, u.getRentals().size());
		for(Rental r : u.getRentals()) {
			assertEquals(u.getId(), r.getUser().getId());
			assertSame(u, r.getUser());
		}
	}

}
