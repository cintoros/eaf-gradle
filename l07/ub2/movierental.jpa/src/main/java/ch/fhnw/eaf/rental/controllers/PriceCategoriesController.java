package ch.fhnw.eaf.rental.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.fhnw.eaf.rental.model.PriceCategory;
import ch.fhnw.eaf.rental.services.MovieService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/pricecategories")
public class PriceCategoriesController {
    private static final Logger logger = LoggerFactory.getLogger(PriceCategoriesController.class);

	@Autowired
	private MovieService movieService;

    @GetMapping
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation"),
        @ApiResponse(responseCode = "204", description = "No content", content = @Content(schema = @Schema(hidden = true))) 
    })
	public ResponseEntity<List<PriceCategory>>  getAllPriceCategories() {
        List<PriceCategory> pc = movieService.getAllPriceCategories();;
        if (pc == null || pc.size() == 0) {
            logger.debug("No pricecategories found");
            new ResponseEntity<List<PriceCategory>>(HttpStatus.NO_CONTENT);
        }
        logger.debug("Successfully returned {} pricecategories", pc.size());
        return new ResponseEntity<List<PriceCategory>>(pc, HttpStatus.OK);
	}

}