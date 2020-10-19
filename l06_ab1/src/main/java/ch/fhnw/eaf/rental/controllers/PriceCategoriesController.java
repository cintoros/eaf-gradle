package ch.fhnw.eaf.rental.controllers;

import ch.fhnw.eaf.rental.model.PriceCategory;
import ch.fhnw.eaf.rental.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pricecategories")
public class PriceCategoriesController {

  @Autowired
  private MovieService movieService;

  @GetMapping
  public List<PriceCategory> getAllPriceCategories() {
    return movieService.getAllPriceCategories();
  }

}
