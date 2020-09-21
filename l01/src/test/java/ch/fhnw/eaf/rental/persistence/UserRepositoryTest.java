package ch.fhnw.eaf.rental.persistence;

import ch.fhnw.eaf.rental.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserRepositoryTest {

  @Autowired
  private UserRepository repo;

  private long totalNumberOfUsers;

  @Before
  public void setUp() {
    totalNumberOfUsers = repo.count();
  }

  @Test
  public void testCount() {
    assertEquals("expected 4 users in the user repo", totalNumberOfUsers, repo.count());
  }

  @Test
  public void testExists() {
    assertTrue("user with id 4 should exist in repo", repo.existsById(4L));
    assertFalse("user with id 44 should not exist in repo", repo.existsById(44L));
  }

  @Test
  public void testDeleteId1() {
    repo.deleteById(4L);
    assertEquals("expected 3 users after deleting movie with id 4", totalNumberOfUsers - 1, repo.count());
  }

  @Test
  public void testDeleteId2() {
    try {
      repo.deleteById(11L);
      fail("expected an exception when deleting a non-existing entity");
    } catch (Exception e) {
      // OK
    }
  }

  @Test
  public void findAll() {
    List<User> list = repo.findAll();
    assertEquals("expected to load 4 users", 4, list.size());
    assertTrue(list.contains(repo.findById(1L).get()));
    assertTrue(list.contains(repo.findById(2L).get()));
    assertTrue(list.contains(repo.findById(3L).get()));
    assertTrue(list.contains(repo.findById(4L).get()));
  }

  @Test
  public void findNonExistentEntity() {
    Optional<User> m = repo.findById(-1L);
    assertFalse(m.isPresent());
  }

}
