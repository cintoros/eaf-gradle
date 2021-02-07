package ch.fhnw.eaf.rental.gui;

public class CreateRentalData {
	public final Long movieId;
	public final Long userId;
	public final int rentalDays;

	public CreateRentalData(Long movieId, Long userId, int rentalDays) {
		super();
		this.movieId = movieId;
		this.userId = userId;
		this.rentalDays = rentalDays;
	}

}
