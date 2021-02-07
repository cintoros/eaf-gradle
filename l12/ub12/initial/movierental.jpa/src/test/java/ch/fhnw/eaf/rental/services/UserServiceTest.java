package ch.fhnw.eaf.rental.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.test.annotation.DirtiesContext;
// import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.transaction.annotation.Transactional;

import ch.fhnw.eaf.rental.model.Movie;
import ch.fhnw.eaf.rental.model.Rental;
import ch.fhnw.eaf.rental.model.User;

@SpringBootTest
// @DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@Transactional
public class UserServiceTest {

	@Autowired
	private UserService userService;

	@Autowired
	private RentalService rentalService;

	@Test
	public void testGetAllUsers() {
		List<User> users = userService.getAllUsers();
		assertEquals(4, users.size());
	}

	@Test
	public void testSaveUser() {
		User user = new User("Tester", "Hugo");
		assertNull(user.getId(), "id must be null as long as object is not yet saved");
		user = userService.save(user);
		assertNotNull(user.getId(), "id must be assigned after insertion into db");
		List<User> users = userService.getAllUsers();
		assertEquals(5, users.size());
	}

	@Test
	public void testCreateUser() {
		User user = new User("Tester", "Hugo");
		user = userService.save(user);
		List<User> users = userService.getAllUsers();
		assertTrue(users.contains(user), "users list must contain new user");
	}

	// Tests whether all rentals are deleted if a user is deleted
	@Test
	public void testDeleteUser() {
		List<Rental> rentals = rentalService.getAllRentals();
		assertEquals(3, rentals.size());
		List<User> users = userService.getUsersByName("Keller");
		assertEquals(1, users.size());
		User user = users.get(0);
		assertEquals(2, user.getRentals().size());
		userService.deleteUser(user);
		rentals = rentalService.getAllRentals();
		assertEquals(1, rentals.size());
	}

	@Test
	public void testGetUsersByName() {
		List<User> users = userService.getUsersByName("Keller");
		assertEquals(1, users.size());
		User user = users.get(0);
		assertEquals("Keller", user.getLastName());
	}
	
	@Test
	public void testDeleteRental() {
		List<User> users = userService.getUsersByName("Keller");
		assertEquals(1, users.size());
		List<Rental> rentals = rentalService.getAllRentals();
		assertEquals(3, rentals.size());
		User user = users.get(0);
		assertEquals("Keller", user.getLastName());
		assertEquals(2, user.getRentals().size());
		Rental rental = user.getRentals().get(1);
		userService.returnMovie(user, rental.getMovie());
		assertEquals(1, user.getRentals().size());
		rentals = rentalService.getAllRentals();
		assertEquals(2, rentals.size());
	}

	@Test
	public void testGetRentalsOfUserUsingLazyLoading() {
		List<User> users = userService.getUsersByName("Keller");
		assertEquals(1, users.size());
		User user = users.get(0);
		assertEquals("Keller", user.getLastName());
		List<Rental> rentals = user.getRentals();
		assertEquals(2, rentals.size());
		Rental rental = rentals.get(0);
		Movie movie = rental.getMovie();
		assertEquals("Marie Curie", movie.getTitle());
	}
	
	@Test
	public void testGetEmailAddress() {
		List<User> users = userService.getUsersByName("Keller");
		assertEquals(1, users.size());
		User user = users.get(0);
		assertEquals("Keller", user.getLastName());
		assertEquals("marc.keller@gmail.com", user.getEmail());

		users = userService.getUsersByName("Knecht");
		assertEquals(1, users.size());
		user = users.get(0);
		assertEquals("Knecht", user.getLastName());
	}
}

