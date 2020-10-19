package ch.fhnw.eaf.rental.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "RENTALS")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Rental.class)

//the following annotations are yoused in the entity manager implementation
@NamedQuery(name = Rental.FIND_ALL, query = "SELECT r FROM Rental r")
@NamedQuery(name = Rental.EXISTS, query = "SELECT COUNT(r) FROM Rental r WHERE r.id = :id")
@NamedQuery(name = Rental.COUNT, query = "SELECT COUNT(r) FROM Rental r")
public class Rental {
	public static final String FIND_ALL = "Rental.all";
	public static final String EXISTS = "Rental.exists";
	public static final String COUNT = "Rental.count";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RENTAL_ID")
	private Long id;

	@OneToOne
	@JoinColumn(name = "MOVIE_ID")
	private Movie movie;

	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;

	@Column(name = "RENTAL_RENTALDATE")
	private LocalDate rentalDate;

	@Column(name = "RENTAL_RENTALDAYS")
	private int rentalDays;

	public Rental() {
		this.rentalDate = LocalDate.now();
	}

	public Rental(User user, Movie movie, int rentalDays) {
		this(user, movie, rentalDays, LocalDate.now());
	}

	public Rental(User user, Movie movie, int rentalDays, LocalDate rentalDate) {
		if (user == null || movie == null || rentalDays <= 0) {
			throw new NullPointerException("not all input parameters are set!" + user + "/" + movie + "/" + rentalDays);
		}
		if (movie.isRented()) {
			throw new IllegalStateException("movie is already rented!");
		}
		this.user = user;
		user.getRentals().add(this);
		this.movie = movie;
		movie.setRented(true);
		this.rentalDays = rentalDays;
		this.rentalDate = rentalDate;
	}

	public double getRentalFee() {
		return movie.getPriceCategory().getCharge(rentalDays);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDate getRentalDate() {
		return rentalDate;
	}

	public void setRentalDate(LocalDate rentalDate) {
		this.rentalDate = rentalDate;
	}

	public int getRentalDays() {
		return rentalDays;
	}

	public void setRentalDays(int rentalDays) {
		this.rentalDays = rentalDays;
	}

}
