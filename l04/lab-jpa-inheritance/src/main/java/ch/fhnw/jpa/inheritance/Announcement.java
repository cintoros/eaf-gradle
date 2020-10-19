package ch.fhnw.jpa.inheritance;

import java.time.LocalDate;

import javax.persistence.Entity;

@Entity
public class Announcement extends Topic {
	private LocalDate validUntil;

	protected Announcement() {
	}

	public Announcement(String title, String owner) {
		this(title, owner, null);
	}

	public Announcement(String title, String owner, LocalDate validUntil) {
		super(title, owner);
		this.validUntil = validUntil;
	}

	public LocalDate getValidUntil() {
		return validUntil;
	}
}
