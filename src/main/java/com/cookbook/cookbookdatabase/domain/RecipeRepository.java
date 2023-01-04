package com.cookbook.cookbookdatabase.domain;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
	
	Iterable<Recipe> findByUser(User user);
	
	// For testing recipe repository.
	Optional<Recipe> findByRecipeName(String recipeName);
}
