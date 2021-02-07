package ch.fhnw.eaf.rental.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import ch.fhnw.eaf.rental.model.Rental;

@SpringBootTest
@Transactional
public class RentalRepositoryTest {

    @Autowired
    private RentalRepository repo;

    private int totalNumberOfRentals;

    @BeforeEach
    public void setUp() {
        totalNumberOfRentals = repo.findAll().size();
    }

    @Test
    public void testCount() {
        assertEquals(totalNumberOfRentals, repo.count(), "expected 3 rentals in the rental repo");
    }

    @Test
    public void testExists() {
        assertTrue(repo.existsById(3L), "rental with id 3 should exist in repo");
        assertFalse(repo.existsById(33L), "rental with id 33 should not exist in repo");
    }

    @Test
    public void testDeleteId1() {
        repo.deleteById(3L);
        assertEquals(totalNumberOfRentals - 1, repo.count(), "expected 2 rentals after deleting rental with id 3");
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
    public void testDelete() {
        Rental r1 = repo.findById(1L).get();
        r1.getMovie().setRented(false);
        Rental r2 = new Rental(r1.getUser(), r1.getMovie(), r1.getRentalDays(), r1.getRentalDate());
        r1.getMovie().setRented(true);
        r2.setId(r1.getId());
        repo.delete(r2);
    }

    @Test
    public void testFindAll() {
        List<Rental> list = repo.findAll();
        assertEquals(totalNumberOfRentals, list.size(), "expected to load 3 rentals");
        assertTrue(list.contains(repo.findById(1L).get()));
        assertTrue(list.contains(repo.findById(2L).get()));
        assertTrue(list.contains(repo.findById(3L).get()));
    }

    @Test
    public void testFindNonExistentEntity() {
        Optional<Rental> m = repo.findById(-1L);
        assertFalse(m.isPresent());
    }

}
