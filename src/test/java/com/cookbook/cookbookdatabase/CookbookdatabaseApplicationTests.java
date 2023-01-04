package com.cookbook.cookbookdatabase;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cookbook.cookbookdatabase.web.AuthRESTController;
import com.cookbook.cookbookdatabase.web.CategoryRESTController;
import com.cookbook.cookbookdatabase.web.IngredientRESTController;
import com.cookbook.cookbookdatabase.web.RecipeRESTController;
import com.cookbook.cookbookdatabase.web.UnitRESTController;

@SpringBootTest
class CookbookdatabaseApplicationTests {
	
	@Autowired
	private AuthRESTController authRESTController;
	@Autowired
	private CategoryRESTController categoryRESTController;
	@Autowired
	private IngredientRESTController ingredientRESTController;
	@Autowired
	private RecipeRESTController recipeRESTController;
	@Autowired
	private UnitRESTController unitRESTController;

	/*
	 * Testing that the instance of the controllers were created and injected successfully.
	 */
	
	@Test
	@DisplayName("Testing controllers.")
	void contextLoads() {
		assertThat(authRESTController).isNotNull();
		assertThat(categoryRESTController).isNotNull();
		assertThat(ingredientRESTController).isNotNull();
		assertThat(recipeRESTController).isNotNull();
		assertThat(unitRESTController).isNotNull();
	}

}
