package ch.fhnw.eaf.rental.controllers;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.fhnw.eaf.rental.model.User;
import ch.fhnw.eaf.rental.services.UserService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/users")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation"),
        @ApiResponse(responseCode = "204", description = "No content", content = @Content(schema = @Schema(hidden = true))) 
    })
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        if (users == null || users.size() == 0) {
            logger.debug("No users found");
            new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
        }
        logger.debug("Successfully returned {} users", users.size());
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @PostMapping
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Entity created"),
        @ApiResponse(responseCode = "412", description = "Invalid input", content = @Content(schema = @Schema(hidden = true))) 
    })
    public ResponseEntity<User> createUser(@Valid @RequestBody User user, BindingResult result) {
        if (result.hasErrors()) {
            logger.error("Could not create user. Precondition failed!");
            return new ResponseEntity<User>(HttpStatus.PRECONDITION_FAILED);
        }
        user = userService.save(user);
        logger.debug("Successfully created user[{}]", user.getId());
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation"),
        @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(hidden = true))) 
    })
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user == null) {
            logger.error("Could not find user id={}", id);
            new ResponseEntity<List<User>>(HttpStatus.NOT_FOUND);
        }
        logger.debug("Successfully returned user[{}]", id);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation"),
        @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(hidden = true))) 
    })
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user == null) {
            logger.error("Could not find user id={}", id);
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        userService.deleteUser(user);
        logger.debug("Successfully deleted user[{}]", id);
        return new ResponseEntity<User>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation"),
        @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(hidden = true))),
        @ApiResponse(responseCode = "412", description = "Invalid input", content = @Content(schema = @Schema(hidden = true))) 
    })
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<User>(HttpStatus.PRECONDITION_FAILED);
        }
        if (userService.getUserById(id) == null) {
            logger.error("Could not find user id={}", id);
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        userService.save(user);
        logger.debug("Successfully updated user[{}]", id);
        return new ResponseEntity<User>(HttpStatus.OK);
    }

}