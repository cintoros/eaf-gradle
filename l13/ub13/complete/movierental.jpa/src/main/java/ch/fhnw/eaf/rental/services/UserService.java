package ch.fhnw.eaf.rental.services;

import java.util.List;

import org.springframework.security.access.annotation.Secured;

import ch.fhnw.eaf.rental.model.Movie;
import ch.fhnw.eaf.rental.model.Rental;
import ch.fhnw.eaf.rental.model.User;

public interface UserService {
	public User getUserById(Long id);

    @Secured({"ROLE_ADMIN"})
	public User save(User user);
    
    @Secured({"ROLE_ADMIN"})
	public void deleteUser(User user);

	public List<User> getAllUsers();

	public List<User> getUsersByName(String name);

    @Secured({"ROLE_ADMIN"})
	public Rental rentMovie(User user, Movie movie, int days);

	public void returnMovie(User user, Movie movie);
}
