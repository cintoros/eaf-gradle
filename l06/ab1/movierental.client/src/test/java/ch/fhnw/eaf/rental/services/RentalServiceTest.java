package ch.fhnw.eaf.rental.services;

import ch.fhnw.eaf.rental.model.Movie;
import ch.fhnw.eaf.rental.model.Rental;
import ch.fhnw.eaf.rental.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class RentalServiceTest {

  @Autowired
  private UserService userService;

  @Autowired
  private MovieService movieService;

  @Autowired
  private RentalService rentalService;

  @Test
  public void testRentMovie() {
    User u = userService.getAllUsers().get(0);
    int size = u.getRentals().size();

    // search a movie which is not yet rented
    Movie m = null;
    List<Movie> movies = movieService.getAllMovies();
    for (Movie mm : movies)
      if (!mm.isRented()) m = mm;

    userService.rentMovie(u, m, 10);
    Assert.assertEquals(size + 1, u.getRentals().size());

    // check whether this movie is also assigned to u in the DB
    for (User uu : userService.getAllUsers())
      if (u.getId().equals(uu.getId())) u = uu;
    Assert.assertEquals(size + 1, u.getRentals().size());
  }

  @Test
  public void testGetAllRentals() {
    List<Rental> rentals = rentalService.getAllRentals();
    Assert.assertEquals(3, rentals.size());
  }

  @Test
  public void testGetAllRentalInfos() {
    List<Rental> rentals = rentalService.getAllRentals();
    Assert.assertEquals(3, rentals.size());
    Rental rental = rentals.get(0);
    Assert.assertNotNull(rental.getUser());
    Assert.assertNotNull(rental.getMovie());
    Assert.assertNotNull(rental.getUser().getEmail());
    Assert.assertNotNull(rental.getMovie().getTitle());
  }

  @Test
  public void testGetAllRentalsByUser() {
    List<User> users = userService.getUsersByName("Keller");
    Assert.assertEquals(1, users.size());
    User user = users.get(0);
    List<Rental> rentals = user.getRentals();
    Assert.assertEquals(2, rentals.size());
  }

  @Test
  public void testReadRental() {
    Rental rental = rentalService.getRentalById(1L);
    User user = rental.getUser();
    Assert.assertEquals("Keller", user.getLastName());
    Assert.assertEquals(2, user.getRentals().size());
  }

  @Test
  public void testDeleteRental() {
    Rental rental = rentalService.getRentalById(1L);
    User user = rental.getUser();
    Assert.assertEquals("Keller", user.getLastName());
    Assert.assertEquals(2, user.getRentals().size());
    rentalService.deleteRental(rental);
    List<Rental> rentals = rentalService.getAllRentals();
    Assert.assertEquals(2, rentals.size());
    user = userService.getUserById(user.getId());
    Assert.assertEquals(1, user.getRentals().size());
  }

//	@Test
//	// this method deletes a new Rental object which is not yet contained in the persistence context.
//	public void testDeleteRental2() {
//		Rental rental = rentalService.getRentalById(1L);
//		User user = rental.getUser();
//		Assert.assertEquals("Keller", user.getLastName());
//		Assert.assertEquals(2, user.getRentals().size());
//		
//		rental.getMovie().setRented(false);
//		Rental r2 = new Rental(rental.getUser(), rental.getMovie(), rental.getRentalDays(), rental.getRentalDate());
//		r2.setId(rental.getId());
//		
//		rentalService.deleteRental(r2);
//		List<Rental> rentals = rentalService.getAllRentals();
//		Assert.assertEquals(2, rentals.size());
//		user = userService.getUserById(user.getId());
//		Assert.assertEquals(1, user.getRentals().size());
//	}

  @Test
  public void loadRental() {
    Rental rental = rentalService.getRentalById(1L);
    User user = rental.getUser();
    Assert.assertTrue("user must contain its rental", user.getRentals().contains(rental));
    System.out.println(user.getRentals().size());
    System.out.println(System.identityHashCode(rental));
    for (Rental r : user.getRentals()) {
      System.out.println(System.identityHashCode(r));
    }
  }
}
