package ch.fhnw.eaf.rental.controllers;

import ch.fhnw.eaf.rental.model.PriceCategory;
import ch.fhnw.eaf.rental.services.MovieService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successful operation"),
      @ApiResponse(responseCode = "204", description = "No content", content = @Content(schema = @Schema(hidden = true)))
  })
  public ResponseEntity<List<PriceCategory>> getAllPriceCategories() {
    List<PriceCategory> movies = movieService.getAllPriceCategories();
    ;
    if (movies == null || movies.size() == 0) {
      new ResponseEntity<List<PriceCategory>>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<List<PriceCategory>>(movies, HttpStatus.OK);
  }

}
