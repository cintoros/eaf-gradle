package ch.fhnw.eaf.rental.controllers;

public class CreateRentalData {
	public final Long movieId;
	public final Long userId;
	public final int rentalDays;
	
	@SuppressWarnings("unused")
	private CreateRentalData() {
		movieId = null;
		userId = null;
		rentalDays = 0;
	}

	public CreateRentalData(Long movieId, Long userId, int rentalDays) {
		super();
		this.movieId = movieId;
		this.userId = userId;
		this.rentalDays = rentalDays;
	}

}
