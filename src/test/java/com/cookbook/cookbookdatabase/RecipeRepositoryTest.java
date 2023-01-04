package com.cookbook.cookbookdatabase;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.cookbook.cookbookdatabase.domain.Recipe;
import com.cookbook.cookbookdatabase.domain.RecipeRepository;


/*
 * Testing saving a new recipe, deleting an existing recipe and updating an existing recipe.
 */

@DataJpaTest
public class RecipeRepositoryTest {
	
	@Autowired
	private RecipeRepository repo;
	
	@Test
	void saveRecipe() {
		repo.save(new Recipe("Testing", null, LocalDateTime.now(), null, null, null, null));
		assertThat(repo.findByRecipeName("Testing").isPresent()).isTrue();
	}
	
	@Test
	void deleteRecipe() {
		// Save a new recipe.
		repo.save(new Recipe("Testing", null, LocalDateTime.now(), null, null, null, null));
		Optional<Recipe> recipe = repo.findByRecipeName("Testing");
		// Delete the recipe.
		repo.delete(recipe.get());
		// Assert that the recipe is not present
		assertThat(repo.findByRecipeName("Testing").isEmpty()).isTrue();
	}
	
	@Test
	void updateRecipe() {
		// Save a new recipe.
		repo.save(new Recipe("Testing", null, LocalDateTime.now(), null, null, null, null));
		// Find the recipe by name.
		Recipe recipe = repo.findByRecipeName("Testing").get();
		// Update the recipe
		recipe.setRecipeName("TestingUpdate");
		repo.save(recipe);
		// Assert that the updated recipe is found.
		assertThat(repo.findByRecipeName("TestingUpdate").isPresent()).isTrue();
		
	}

}
