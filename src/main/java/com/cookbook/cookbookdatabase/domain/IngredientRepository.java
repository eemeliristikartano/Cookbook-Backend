package com.cookbook.cookbookdatabase.domain;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
	
	// For testing ingredient repository.
	Optional<Ingredient> findByIngredientName(String ingredientName);
	
}
