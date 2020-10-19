package ch.fhnw.eaf.rental.controllers;

import ch.fhnw.eaf.rental.model.User;
import ch.fhnw.eaf.rental.services.UserService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

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
      new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<List<User>>(users, HttpStatus.OK);
  }

  @PostMapping
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Entity created"),
      @ApiResponse(responseCode = "412", description = "Invalid input", content = @Content(schema = @Schema(hidden = true)))
  })
  public ResponseEntity<User> createUser(@Valid @RequestBody User user, BindingResult result) {
    if (result.hasErrors()) {
      return new ResponseEntity<User>(HttpStatus.PRECONDITION_FAILED);
    }
    user = userService.save(user);
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
      new ResponseEntity<List<User>>(HttpStatus.NOT_FOUND);
    }
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
      return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }
    userService.deleteUser(user);
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
      return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }
    userService.save(user);
    return new ResponseEntity<User>(HttpStatus.OK);
  }

}
