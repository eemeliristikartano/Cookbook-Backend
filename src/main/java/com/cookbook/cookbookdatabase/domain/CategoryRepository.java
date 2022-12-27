package com.cookbook.cookbookdatabase.domain;

import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
	
	Category findByName(String categoryName);

}
