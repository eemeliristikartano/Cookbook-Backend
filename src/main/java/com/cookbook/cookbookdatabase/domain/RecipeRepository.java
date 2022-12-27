package com.cookbook.cookbookdatabase.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin("http://localhost:3000")
public interface RecipeRepository extends CrudRepository<Recipe, Long> {

}
