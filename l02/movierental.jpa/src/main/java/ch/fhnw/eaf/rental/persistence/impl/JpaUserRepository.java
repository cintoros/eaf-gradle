package ch.fhnw.eaf.rental.persistence.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import ch.fhnw.eaf.rental.model.User;
import ch.fhnw.eaf.rental.persistence.UserRepository;

@Repository
public class JpaUserRepository implements UserRepository {

  @Override
  public Optional<User> findById(Long id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<User> findAll() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public User save(User t) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void deleteById(Long id) {
    // TODO Auto-generated method stub

  }

  @Override
  public void delete(User entity) {
    // TODO Auto-generated method stub

  }

  @Override
  public boolean existsById(Long id) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public long count() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public List<User> findByLastName(String lastName) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<User> findByFirstName(String firstName) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<User> findByEmail(String email) {
    // TODO Auto-generated method stub
    return null;
  }

}
