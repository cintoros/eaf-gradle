package ch.fhnw.eaf.rental.controllers;

import ch.fhnw.eaf.rental.model.User;
import ch.fhnw.eaf.rental.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/movierental")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping(path = "/users")
  public List<User> getAllUsers() {
    return userService.getAllUsers();
  }

  @PostMapping(path = "/users")
  public User createUser(@RequestBody User user) {
    userService.save(user);
    return user;
  }

  @GetMapping(path = "/users/{id}")
  public User getUser(@PathVariable Long id) {
    return userService.getUserById(id);
  }

  @DeleteMapping(path = "/users/{id}")
  public void deleteUser(@PathVariable Long id) {
    userService.deleteUser(userService.getUserById(id));
  }

  @PutMapping(path = "/users/{id}")
  public void updateUser(@PathVariable Long id, @RequestBody User user) {
    userService.save(user);
  }

}
