package ch.fhnw.eaf.rental.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import ch.fhnw.eaf.rental.json.PriceCategoryDeserializer;
import ch.fhnw.eaf.rental.json.PriceCategorySerializer;

@Entity
@Table(name = "PRICECATEGORIES")
@DiscriminatorColumn(name = "PRICECATEGORY_TYPE")
@JsonDeserialize(using = PriceCategoryDeserializer.class)
@JsonSerialize(using = PriceCategorySerializer.class)

//the following annotations are yoused in the entity manager implementation
@NamedQuery(name = PriceCategory.FIND_ALL, query = "SELECT c FROM PriceCategory c")
@NamedQuery(name = PriceCategory.EXISTS, query = "SELECT COUNT(c) FROM PriceCategory c WHERE c.id = :id")
@NamedQuery(name = PriceCategory.COUNT, query = "SELECT COUNT(c) FROM PriceCategory c")
public abstract class PriceCategory {
	public static final String FIND_ALL = "PriceCategory.all";
	public static final String EXISTS = "PriceCategory.exists";
	public static final String COUNT = "PriceCategory.count";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PRICECATEGORY_ID")
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public abstract double getCharge(int daysRented);

	public int getFrequentRenterPoints(int daysRented) {
		return 1;
	}
}
