package ch.fhnw.eaf.rental.persistence.impl;

import ch.fhnw.eaf.rental.model.Rental;
import ch.fhnw.eaf.rental.model.User;
import ch.fhnw.eaf.rental.persistence.RentalRepository;
import ch.fhnw.eaf.rental.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserRepositoryImpl implements UserRepository {
  private Map<Long, User> data = new HashMap<Long, User>();
  private long nextId = 1;

  @Autowired
  private RentalRepository rentalRepo;

  @SuppressWarnings("unused")
  private void initData() {
    data.clear();
    nextId = 1;
    createUser("Keller", "Marc", "marc.keller@gmail.com");
    createUser("Knecht", "Werner", "werner.knecht@gmail.com");
    createUser("Meyer", "Barbara", "barbara.meyer@gmail.com");
    createUser("Kummer", "Adolf", "adolf.kummer@gmail.com");
  }

  private void createUser(String lastName, String firstName, String email) {
    User u = new User(lastName, firstName);
    u.setEmail(email);
    save(u);
  }

  @Override
  public Optional<User> findById(Long id) {
    if (id == null)
      throw new IllegalArgumentException();
    return Optional.ofNullable(data.get(id));
  }

  @Override
  public List<User> findAll() {
    return new ArrayList<User>(data.values());
  }

  @Override
  public User save(User user) {
    if (user.getId() == null) user.setId(nextId++);
    data.put(user.getId(), user);
    return user;
  }

  @Override
  public void delete(User user) {
    if (user == null) throw new IllegalArgumentException();
    if (data.get(user.getId()) == null)
      throw new RuntimeException("User entity with id " + user.getId() + " does not exixt");
    for (Rental r : user.getRentals()) {
      rentalRepo.delete(r);
    }
    data.remove(user.getId());
    user.setId(null);
  }

  @Override
  public void deleteById(Long id) {
    if (id == null) throw new IllegalArgumentException();
    findById(id).ifPresentOrElse(e -> delete(e), () -> {
      throw new RuntimeException("User entity with id " + id + " does not exixt");
    });
  }

  @Override
  public boolean existsById(Long id) {
    if (id == null) throw new IllegalArgumentException();
    return data.get(id) != null;
  }

  @Override
  public long count() {
    return data.size();
  }

  @Override
  public List<User> findByLastName(String lastName) {
    List<User> result = new ArrayList<>();
    for (User u : data.values()) {
      if (u.getLastName().equals(lastName)) result.add(u);
    }
    return result;
  }

  @Override
  public List<User> findByFirstName(String firstName) {
    List<User> result = new ArrayList<>();
    for (User u : data.values()) {
      if (u.getFirstName().equals(firstName)) result.add(u);
    }
    return result;
  }

  @Override
  public List<User> findByEmail(String email) {
    List<User> result = new ArrayList<>();
    for (User u : data.values()) {
      if (u.getEmail().equals(email)) result.add(u);
    }
    return result;
  }

}
