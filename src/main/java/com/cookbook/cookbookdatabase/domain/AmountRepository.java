package com.cookbook.cookbookdatabase.domain;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface AmountRepository extends CrudRepository<Amount, Long> {
	
	// For testing.
	Optional<Amount> findByQuantity(String quantity);
	
}
