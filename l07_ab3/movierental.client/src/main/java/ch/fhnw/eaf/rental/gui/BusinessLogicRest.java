package ch.fhnw.eaf.rental.gui;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import ch.fhnw.eaf.rental.model.Movie;
import ch.fhnw.eaf.rental.model.PriceCategory;
import ch.fhnw.eaf.rental.model.Rental;
import ch.fhnw.eaf.rental.model.User;

@Component
public class BusinessLogicRest implements BusinessLogic {

	public BusinessLogicRest() {
		System.out.println("BusinessLogicRest created");
	}
	
	@Value("${server:http://localhost:8080}")
	String server;


	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	public String getUserLastName(Long id) {
		return getUser(id).getLastName();
	}

	public String getUserFirstName(Long id) {
		return getUser(id).getFirstName();
	}

	public int getUserRentalsSize(Long id) {
		return getUser(id).getRentals().size();
	}

	public String getMovieTitle(Long id) {
		return getMovie(id).getTitle();
	}

	public String getMoviePriceCategory(Long id) {
		return getMovie(id).getPriceCategory().toString();
	}

	public LocalDate getMovieReleaseDate(Long id) {
		return getMovie(id).getReleaseDate();
	}

	public boolean getMovieIsRented(Long id) {
		return getMovie(id).isRented();
	}

	private User getUser(Long id) {
		return restTemplate().getForEntity(server + "/movierental/users/{id}", User.class, id).getBody();
	}

	private Movie getMovie(Long id) {
		return restTemplate().getForEntity(server + "/movierental/movies/{id}", Movie.class, id).getBody();
	}

	@SuppressWarnings("unused")
	private Rental getRental(Long id) {
		return restTemplate().getForEntity(server + "/movierental/rentals/{id}", Rental.class, id).getBody();
	}

	public void removeRental(Long rentalId) {
		restTemplate().delete(server + "/movierental/rentals/{id}", rentalId);
	}

	public void deleteUser(Long userId) {
		restTemplate().delete(server + "/movierental/users/{id}", userId);
	}

	public void updateUser(Long userId, String lastName, String firstName) {
		// this method only changes the name of the user, but the referenced rentals are
		// not changed.
		User user = new User(lastName, firstName);
		user.setId(userId);
		restTemplate().put(server + "/movierental/users/{id}", user, userId);
	}

	public Long createUser(String lastName, String firstName) {
		User user = new User(lastName, firstName);
		user = restTemplate().postForEntity(server + "/movierental/users/", user, User.class).getBody();
		return user.getId();
	}

	public void deleteMovie(Long movieId) {
		restTemplate().delete(server + "/movierental/movies/{id}", movieId);
	}
	
	private List<PriceCategory> getPriceCategories() {
		// the following does not work: https://www.baeldung.com/spring-rest-template-list
		// List<Movie> movies = restTemplate().getForEntity(server + "/movierental/pricecategories/", List.class).getBody();
		ResponseEntity<List<PriceCategory>> response = restTemplate().exchange(
				server + "/movierental/pricecategories/",
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<PriceCategory>>() {
		});
		return response.getBody();
	}

	public Long createMovie(String movieTitle, LocalDate releaseDate, String category) {
		Movie movie = null;
		for (PriceCategory pc : getPriceCategories()) {
			if (pc.toString().equals(category)) {
				movie = new Movie(movieTitle, releaseDate, pc);
			}
		}
		movie = restTemplate().postForEntity(server + "/movierental/movies/", movie, Movie.class).getBody();
		return movie.getId();
	}

	public void updateMovie(Long movieId, String movieTitle, LocalDate date, String category) {
		// only called when movie is updated
		Movie orig = getMovie(movieId);
		Movie movie = null;
		for (PriceCategory pc : getPriceCategories()) {
			if (pc.toString().equals(category)) {
				movie = new Movie(movieTitle, date, pc);
			}
		}
		movie.setId(movieId);
		movie.setRented(orig.isRented());
		restTemplate().put(server + "/movierental/movies/{id}", movie, movieId);
	}

	public Long createRental(Long movieId, Long userId, Integer rentalDays) {
		CreateRentalData data = new CreateRentalData(movieId, userId, rentalDays);
		Rental rental = restTemplate().postForEntity(server + "/movierental/rentals/", data, Rental.class).getBody();
		return rental.getId();
	}

	public void visitUsers(UserVisitor visitor) {
		// the following does not work: https://www.baeldung.com/spring-rest-template-list
		// List<User> users = restTemplate().getForEntity(server + "/movierental/users/", List.class).getBody();
		ResponseEntity<List<User>> response = restTemplate().exchange(
				server + "/movierental/users/",
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<User>>() {
		});
		List<User> users = response.getBody();
		
		for (User u : users) {
			visitor.visit(u.getId(), u.getLastName(), u.getFirstName());
		}
	}

	public void visitMovies(MovieVisitor visitor) {
		// the following does not work: https://www.baeldung.com/spring-rest-template-list
		// List<Movie> movies = restTemplate().getForEntity(server + "/movierental/movies/", List.class).getBody();
		ResponseEntity<List<Movie>> response = restTemplate().exchange(
				server + "/movierental/movies/",
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<Movie>>() {
		});
		List<Movie> movies = response.getBody();
		
		for (Movie m : movies) {
			visitor.visit(m.getId(), m.getTitle(), m.getReleaseDate(), m.isRented(), m.getPriceCategory().toString());
		}
	}

	public void visitRentals(RentalVisitor visitor) {
		// the following does not work: https://www.baeldung.com/spring-rest-template-list
		// List<Rental> rentals = restTemplate().getForEntity(server + "/movierental/rentals/", List.class).getBody();
		
		// the following does not work due to the Serialization (i.e. objects are serialized several times).
		// As an alternative all users are loaded and their rentals are filtered out.
		ResponseEntity<List<Rental>> response = restTemplate().exchange(
				server + "/movierental/rentals/",
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<Rental>>() {
		});
		List<Rental> rentals = response.getBody();

		// Workaround if the above code does not work
//		ResponseEntity<List<User>> response = restTemplate().exchange(
//				server + "/movierental/users/",
//				HttpMethod.GET,
//				null,
//				new ParameterizedTypeReference<List<User>>() {
//		});
//		List<User> users = response.getBody();
//		List<Rental> rentals = users.stream().flatMap(u -> u.getRentals().stream()).collect(Collectors.toList());
		
		for (Rental r : rentals) {
			User user = r.getUser();
			Movie movie = r.getMovie();
			int remainingDays = r.getRentalDays() - (int) ChronoUnit.DAYS.between(r.getRentalDate(), LocalDate.now());
			visitor.visit(r.getId(), r.getRentalDays(), r.getRentalDate(), user.getLastName(), user.getFirstName(),
					movie.getTitle(), remainingDays, r.getRentalFee());
		}
	}

	public void visitRentalsOfUser(Long userId, RentalVisitor visitor) {
		User user = getUser(userId);
		for(Rental r : user.getRentals()) {
			int remainingDays = r.getRentalDays() - (int)ChronoUnit.DAYS.between(r.getRentalDate(), LocalDate.now());
			visitor.visit(r.getId(), r.getRentalDays(), r.getRentalDate(), user.getLastName(), user.getFirstName(), r.getMovie().getTitle(), remainingDays, r.getRentalFee());
		}
	}

}
