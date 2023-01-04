package com.cookbook.cookbookdatabase;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.cookbook.cookbookdatabase.domain.Unit;
import com.cookbook.cookbookdatabase.domain.UnitRepository;


/*
 * Testing saving a new unit, deleting an existing unit and updating an existing unit.
 */

@DataJpaTest
public class UnitRepositoryTest {
	
	@Autowired
	private UnitRepository repo;
	
	@Test
	void saveUnit() {
		repo.save(new Unit("Testing"));
		assertThat(repo.findByUnit("Testing")).isNotNull();
	}
	
	@Test
	void deleteUnit() {
		// Save a new Unit.
		repo.save(new Unit("Testing"));
		// Checking how many saved categories there are.
		long count = repo.count();
		Unit unit = repo.findByUnit("Testing");
		// Delete the Unit.
		repo.delete(unit);
		// Checking how many saved categories there are after deletion.
		long countAfterDeletion = repo.count();
		// Assert that there are now less saved categories.
		assertThat(countAfterDeletion).isLessThan(count);
	}
	
	@Test
	void updateUnit() {
		// Save a new Unit.
		repo.save(new Unit("Testing"));
		// Find the Unit by name.
		Unit unit = repo.findByUnit("Testing");
		// Update the Unit
		unit.setUnit("TestingUpdate");
		repo.save(unit);
		// Assert that the updated Unit is found.
		assertThat(repo.findByUnit("TestingUpdate")).isNotNull();
		
	}

}
