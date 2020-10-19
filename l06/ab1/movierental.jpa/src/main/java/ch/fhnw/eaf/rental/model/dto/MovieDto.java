package ch.fhnw.eaf.rental.model.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class MovieDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String title;
	private LocalDate releaseDate;
	private boolean rented;
	private Long priceCategoryId;

	public String toString() {
		return String.format("MovieDto(%s, %s, %s, %s, %s)", id, title, releaseDate, rented, priceCategoryId);
	}

	public MovieDto(){};
	public MovieDto(String title, LocalDate releaseDate, Long priceCategoryId) throws NullPointerException {
		if ((title == null) || (releaseDate == null) || (priceCategoryId == 0)) {
			throw new NullPointerException("not all input parameters are set!");
		}
		this.title = title;
		this.releaseDate = releaseDate;
		this.priceCategoryId = priceCategoryId;
		this.rented = false;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getPriceCategoryId() {
		return priceCategoryId;
	}

	public void setPriceCategoryId(Long priceCategoryId) {
		this.priceCategoryId = priceCategoryId;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((priceCategoryId == null) ? 0 : priceCategoryId.hashCode());
		result = prime * result + ((releaseDate == null) ? 0 : releaseDate.hashCode());
		result = prime * result + (rented ? 1231 : 1237);
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MovieDto other = (MovieDto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (priceCategoryId == null) {
			if (other.priceCategoryId != null)
				return false;
		} else if (!priceCategoryId.equals(other.priceCategoryId))
			return false;
		if (releaseDate == null) {
			if (other.releaseDate != null)
				return false;
		} else if (!releaseDate.equals(other.releaseDate))
			return false;
		if (rented != other.rented)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
}
