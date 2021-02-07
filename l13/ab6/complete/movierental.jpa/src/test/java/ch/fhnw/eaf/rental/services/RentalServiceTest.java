package ch.fhnw.eaf.rental.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
@Transactional
// @DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
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
            if (!mm.isRented())
                m = mm;

        userService.rentMovie(u, m, 10);
        assertEquals(size + 1, u.getRentals().size());

        // check whether this movie is also assigned to u in the DB
        for (User uu : userService.getAllUsers())
            if (u.getId().equals(uu.getId()))
                u = uu;
        assertEquals(size + 1, u.getRentals().size());
    }

    @Test
    public void testGetAllRentals() {
        List<Rental> rentals = rentalService.getAllRentals();
        assertEquals(3, rentals.size());
    }

    @Test
    public void testGetAllRentalInfos() {
        List<Rental> rentals = rentalService.getAllRentals();
        assertEquals(3, rentals.size());
        Rental rental = rentals.get(0);
        assertNotNull(rental.getUser());
        assertNotNull(rental.getMovie());
        assertNotNull(rental.getUser().getEmail());
        assertNotNull(rental.getMovie().getTitle());
    }

    @Test
    public void testGetAllRentalsByUser() {
        List<User> users = userService.getUsersByName("Keller");
        assertEquals(1, users.size());
        User user = users.get(0);
        List<Rental> rentals = user.getRentals();
        assertEquals(2, rentals.size());
    }

    @Test
    public void testReadRental() {
        Rental rental = rentalService.getRentalById(1L);
        User user = rental.getUser();
        assertEquals("Keller", user.getLastName());
        assertEquals(2, user.getRentals().size());
    }

    @Test
    public void testDeleteRental() {
        Rental rental = rentalService.getRentalById(1L);
        User user = rental.getUser();
        assertEquals("Keller", user.getLastName());
        assertEquals(2, user.getRentals().size());
        rentalService.deleteRental(rental);
        List<Rental> rentals = rentalService.getAllRentals();
        assertEquals(2, rentals.size());
        user = userService.getUserById(user.getId());
        assertEquals(1, user.getRentals().size());
    }

    // @Test
    // // this method deletes a new Rental object which is not yet contained in the
    // persistence context.
    // public void testDeleteRental2() {
    // Rental rental = rentalService.getRentalById(1L);
    // User user = rental.getUser();
    // Assert.assertEquals("Keller", user.getLastName());
    // Assert.assertEquals(2, user.getRentals().size());
    //
    // rental.getMovie().setRented(false);
    // Rental r2 = new Rental(rental.getUser(), rental.getMovie(),
    // rental.getRentalDays(), rental.getRentalDate());
    // r2.setId(rental.getId());
    //
    // rentalService.deleteRental(r2);
    // List<Rental> rentals = rentalService.getAllRentals();
    // Assert.assertEquals(2, rentals.size());
    // user = userService.getUserById(user.getId());
    // Assert.assertEquals(1, user.getRentals().size());
    // }

    @Test
    public void loadRental() {
        Rental rental = rentalService.getRentalById(1L);
        User user = rental.getUser();
        assertTrue(user.getRentals().contains(rental), "user must contain its rental");
        // System.out.println(user.getRentals().size());
        // System.out.println(System.identityHashCode(rental));
        for (Rental r : user.getRentals()) {
            System.out.println(System.identityHashCode(r));
        }
    }
}
