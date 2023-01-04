package com.cookbook.cookbookdatabase;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.cookbook.cookbookdatabase.domain.Ingredient;
import com.cookbook.cookbookdatabase.domain.IngredientRepository;


/*
 * Testing saving a new ingredient, deleting an existing ingredient and updating an existing ingredient.
 */

@DataJpaTest
public class IngredientRepositoryTest {
	
	@Autowired
	private IngredientRepository repo;
	
	@Test
	void saveIngredient() {
		repo.save(new Ingredient("Testing", null, null));
		assertThat(repo.findByIngredientName("Testing").isPresent()).isTrue();
	}
	
	@Test
	void deleteIngredient() {
		// Save a new ingredient.
		repo.save(new Ingredient("Testing", null, null));
		Optional<Ingredient> ingredient = repo.findByIngredientName("Testing");
		// Delete the ingredient.
		repo.delete(ingredient.get());
		// Assert that the ingredient is not present
		assertThat(repo.findByIngredientName("Testing").isEmpty()).isTrue();
	}
	
	@Test
	void updateIngredient() {
		// Save a new ingredient.
		repo.save(new Ingredient("Testing", null, null));
		// Find the ingredient by name.
		Ingredient ingredient = repo.findByIngredientName("Testing").get();
		// Update the ingredient
		ingredient.setIngredientName("TestingUpdate");
		repo.save(ingredient);
		// Assert that the updated ingredient is found.
		assertThat(repo.findByIngredientName("TestingUpdate").isPresent()).isTrue();
		
	}


}
