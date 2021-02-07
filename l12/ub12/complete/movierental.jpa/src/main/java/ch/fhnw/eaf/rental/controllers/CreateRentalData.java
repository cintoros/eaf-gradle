package ch.fhnw.eaf.rental.controllers;

import javax.validation.constraints.NotNull;

public class CreateRentalData {
    @NotNull
	public final Long movieId;
    @NotNull
	public final Long userId;
    @NotNull
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
