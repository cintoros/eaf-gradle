package ch.fhnw.eaf.rental.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "USERS")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Rental.class)

//the following annotations are yoused in the entity manager implementation
@NamedQuery(name = User.FIND_ALL, query = "SELECT u FROM User u")
@NamedQuery(name = User.EXISTS, query = "SELECT COUNT(u) FROM User u WHERE u.id = :id")
@NamedQuery(name = User.COUNT, query = "SELECT COUNT(u) FROM User u")
@NamedQuery(name = User.FIND_BY_LAST_NAME, query = "SELECT u FROM User u WHERE u.lastName = :name")
@NamedQuery(name = User.FIND_BY_FIRST_NAME, query = "SELECT u FROM User u WHERE u.firstName = :name")
@NamedQuery(name = User.FIND_BY_EMAIL, query = "SELECT u FROM User u WHERE u.email = :email")
public class User {
	public static final String FIND_ALL = "User.all";
	public static final String FIND_BY_LAST_NAME = "User.byLastName";
	public static final String FIND_BY_FIRST_NAME = "User.byFirstName";
	public static final String FIND_BY_EMAIL = "User.byTitle";
	public static final String EXISTS = "User.exists";
	public static final String COUNT = "User.count";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private Long id;

	@Column(name = "USER_NAME")
	private String lastName;

	@Column(name = "USER_FIRSTNAME")
	private String firstName;

	@Column(name = "USER_EMAIL")
	private String email;

	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	private List<Rental> rentals;

	protected User() { }

	public User(String lastName, String firstName) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.rentals = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String name) {
		this.lastName = name;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Rental> getRentals() {
		return rentals;
	}

	public void setRentals(List<Rental> rentals) {
		this.rentals = rentals;
	}

	public int getCharge() {
		int result = 0;
		for (Rental rental : rentals) {
			result += rental.getMovie().getPriceCategory().getCharge(rental.getRentalDays());
		}
		return result;
	}

}
