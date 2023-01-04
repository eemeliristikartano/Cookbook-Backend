package com.cookbook.cookbookdatabase;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.cookbook.cookbookdatabase.domain.Category;
import com.cookbook.cookbookdatabase.domain.CategoryRepository;


/*
 * Testing saving a new category, deleting an existing category and updating an existing category.
 */

@DataJpaTest
public class CategoryRepositoryTest {
	
	@Autowired
	private CategoryRepository repo;
	
	@Test
	void saveCategory() {
		repo.save(new Category("Testing"));
		assertThat(repo.findByName("Testing")).isNotNull();
	}
	
	@Test
	void deleteCategory() {
		// Save a new category.
		repo.save(new Category("Testing"));
		// Checking how many saved categories there are.
		long count = repo.count();
		Category category = repo.findByName("Testing");
		// Delete the category.
		repo.delete(category);
		// Checking how many saved categories there are after deletion.
		long countAfterDeletion = repo.count();
		// Assert that there are now less saved categories.
		assertThat(countAfterDeletion).isLessThan(count);
	}
	
	@Test
	void updateCategory() {
		// Save a new category.
		repo.save(new Category("Testing"));
		// Find the category by name.
		Category category = repo.findByName("Testing");
		// Update the category
		category.setName("TestingUpdate");
		repo.save(category);
		// Assert that the updated category is found.
		assertThat(repo.findByName("TestingUpdate")).isNotNull();
		
	}

}
