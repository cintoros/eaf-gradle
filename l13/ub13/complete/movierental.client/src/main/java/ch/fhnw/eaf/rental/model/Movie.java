package ch.fhnw.eaf.rental.model;

import java.time.LocalDate;

public class Movie {
	private Long id;

	private final String title;
	private final LocalDate releaseDate;
	private boolean rented;
	private PriceCategory priceCategory;

	@SuppressWarnings("unused")
	private Movie() {
		title = null;
		releaseDate = null;
	}

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

	public LocalDate getReleaseDate() {
		return releaseDate;
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
