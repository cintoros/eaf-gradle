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
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "MOVIES")

// the following annotations are yoused in the entity manager implementation
@NamedQuery(name = Movie.FIND_ALL, query = "SELECT m FROM Movie m")
@NamedQuery(name = Movie.EXISTS, query = "SELECT COUNT(m) FROM Movie m WHERE m.id = :id")
@NamedQuery(name = Movie.COUNT, query = "SELECT COUNT(m) FROM Movie m")
@NamedQuery(name = Movie.FIND_BY_TITLE, query = "SELECT m FROM Movie m WHERE m.title = :title")
public class Movie {
	public static final String FIND_ALL = "Movie.all";
	public static final String FIND_BY_TITLE = "Movie.byTitle";
	public static final String EXISTS = "Movie.exists";
	public static final String COUNT = "Movie.count";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MOVIE_ID")
	private Long id;

    @Column(name = "MOVIE_TITLE")
    @NotEmpty
	private String title;

    @Column(name = "MOVIE_RELEASEDATE")
	private LocalDate releaseDate;

	@Column(name = "MOVIE_RENTED")
	private boolean rented;

	@ManyToOne
	@JoinColumn(name = "PRICECATEGORY_FK")
	private PriceCategory priceCategory;

	// MapStruct requires a public constructor
	public Movie() { }

	public Movie(Long id, String title, LocalDate releaseDate, boolean rented, PriceCategory priceCategory) {
		this(title, releaseDate, priceCategory);
		setId(id);
		setRented(rented);
	}

	public Movie(String title, LocalDate releaseDate, PriceCategory priceCategory) {
		if ((title == null) || (releaseDate == null) || (priceCategory == null)) {
			throw new NullPointerException("not all input parameters are set!");
		}
		this.title = title;
		this.releaseDate = releaseDate;
		this.priceCategory = priceCategory;
		this.rented = false;
	}

	public PriceCategory getPriceCategory() {
		return priceCategory;
	}

	public void setPriceCategory(PriceCategory priceCategory) {
		this.priceCategory = priceCategory;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	public boolean isRented() {
		return rented;
	}

	public void setRented(boolean rented) {
		this.rented = rented;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
