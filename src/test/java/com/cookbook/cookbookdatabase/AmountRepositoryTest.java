package com.cookbook.cookbookdatabase;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.cookbook.cookbookdatabase.domain.Amount;
import com.cookbook.cookbookdatabase.domain.AmountRepository;


/*
 * Testing saving a new amount, deleting an existing amount and updating an existing amount.
 */

@DataJpaTest
public class AmountRepositoryTest {
	
	@Autowired
	private AmountRepository repo;
	
	@Test
	void saveAmount() {
		repo.save(new Amount("Testing", null));
		assertThat(repo.findByQuantity("Testing").isPresent()).isTrue();
	}
	
	@Test
	void deleteAmount() {
		// Save a new amount.
		repo.save(new Amount("Testing", null));
		// Checking how many saved amounts there is.
		long count = repo.count();
		Amount amount = repo.findByQuantity("Testing").get();
		// Delete the amount.
		repo.delete(amount);
		// Checking how many saved amounts there is after deletion.
		long countAfterDeletion = repo.count();
		// Assert that there is now less saved amounts.
		assertThat(countAfterDeletion).isLessThan(count);
	}
	
	@Test
	void updateAmount() {
		// Save a new amount.
		repo.save(new Amount("Testing", null));
		// Find the amount by name.
		Amount amount = repo.findByQuantity("Testing").get();
		// Update the amount
		amount.setQuantity("TestingUpdate");
		repo.save(amount);
		// Assert that the updated amount is found.
		assertThat(repo.findByQuantity("TestingUpdate").isPresent()).isTrue();
		
	}

}
