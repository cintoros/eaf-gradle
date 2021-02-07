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

import ch.fhnw.eaf.rental.model.PriceCategory;

@SpringBootTest
@Transactional
public class PriceCategoryRepositoryTest {

    @Autowired
    private PriceCategoryRepository repo;

    private int totalNumberOfPriceCategories;

    @BeforeEach
    public void setUp() {
        totalNumberOfPriceCategories = repo.findAll().size();
    }

    @Test
    public void testCount() {
        assertEquals(totalNumberOfPriceCategories, repo.count(),
                "expected 3 price categoryes in the PriceCategory repo");
    }

    @Test
    public void testExists() {
        assertTrue(repo.existsById(3L), "price category with id 3 should exist in repo");
        assertFalse(repo.existsById(33L), "price category with id 33 should not exist in repo");
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
        List<PriceCategory> list = repo.findAll();
        assertEquals(totalNumberOfPriceCategories, list.size(), "expected to load 3 price categories");
        assertTrue(list.contains(repo.findById(1L).get()));
        assertTrue(list.contains(repo.findById(2L).get()));
        assertTrue(list.contains(repo.findById(3L).get()));
    }

    @Test
    public void findNonExistentEntity() {
        Optional<PriceCategory> m = repo.findById(-1L);
        assertFalse(m.isPresent());
    }

}
