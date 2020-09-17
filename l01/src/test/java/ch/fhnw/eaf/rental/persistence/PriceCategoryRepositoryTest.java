package ch.fhnw.eaf.rental.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import ch.fhnw.eaf.rental.model.PriceCategory;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PriceCategoryRepositoryTest {

	@Autowired
	private PriceCategoryRepository repo;

	private int totalNumberOfPriceCategories;

	@Before
	public void setUp() {
		totalNumberOfPriceCategories = repo.findAll().size();
	}

	@Test
	public void testCount() {
		assertEquals("expected 3 price categoryes in the PriceCategory repo", totalNumberOfPriceCategories, repo.count());
	}

	@Test
	public void testExists() {
		assertTrue("price category with id 3 should exist in repo", repo.existsById(3L));
		assertFalse("price category with id 33 should not exist in repo", repo.existsById(33L));
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
		assertEquals("expected to load 3 price categories", totalNumberOfPriceCategories, list.size());
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
