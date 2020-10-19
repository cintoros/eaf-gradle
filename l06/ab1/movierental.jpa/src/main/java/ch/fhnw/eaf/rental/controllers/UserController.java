package ch.fhnw.eaf.rental.controllers;

import ch.fhnw.eaf.rental.model.User;
import ch.fhnw.eaf.rental.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping
  public List<User> getAllUsers() {
    return userService.getAllUsers();
  }

  @PostMapping
  public User createUser(@RequestBody User user) {
    userService.save(user);
    return user;
  }

  @GetMapping("/{id}")
  public User getUser(@PathVariable Long id) {
    return userService.getUserById(id);
  }

  @DeleteMapping("/{id}")
  public void deleteUser(@PathVariable Long id) {
    userService.deleteUser(userService.getUserById(id));
  }

  @PutMapping("/{id}")
  public void updateUser(@PathVariable Long id, @RequestBody User user) {
    userService.save(user);
  }

}
