package ch.fhnw.eaf.rental.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.fhnw.eaf.rental.model.PriceCategory;
import ch.fhnw.eaf.rental.services.MovieService;

@RestController
@RequestMapping(path = "/movierental")
public class PriceCategoriesController {

	@Autowired
	private MovieService movieService;

	@GetMapping(path = "/pricecategories")
	public List<PriceCategory> getAllPriceCategories() {
		return movieService.getAllPriceCategories();
	}

}