package com.cookbook.cookbookdatabase.web;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cookbook.cookbookdatabase.domain.AmountRepository;
import com.cookbook.cookbookdatabase.domain.CategoryRepository;
import com.cookbook.cookbookdatabase.domain.Ingredient;
import com.cookbook.cookbookdatabase.domain.IngredientRepository;
import com.cookbook.cookbookdatabase.domain.Recipe;
import com.cookbook.cookbookdatabase.domain.RecipeRepository;
import com.cookbook.cookbookdatabase.domain.UnitRepository;
import com.cookbook.cookbookdatabase.domain.User;
import com.cookbook.cookbookdatabase.domain.UserRepository;

@RestController
@CrossOrigin
public class RecipeRESTController {
		
	@Autowired
	private RecipeRepository recipeRepo;
	
	@Autowired
	private IngredientRepository ingredientRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private AmountRepository amountRepo;
	
	@Autowired
	private UnitRepository unitRepo;
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	@GetMapping(value = "/recipes")
	public @ResponseBody Iterable<Recipe> getRecipes() {
		return recipeRepo.findAll();
	}
	
	/*
	 * User's own recipes.
	 */
	
	@GetMapping(value = "/userrecipes")
	public @ResponseBody Iterable<Recipe> getUserRecipes() {
		String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userRepo.findByUsername(username).get();
		return recipeRepo.findByUser(user);
	}
	
	@PostMapping(value = "/deleterecipe")
	public @ResponseBody void deleteRecipe(@RequestBody Long recipeId) {
		recipeRepo.deleteById(recipeId);
	}
	
	@PostMapping(value = "/saverecipe")
	public @ResponseBody void saveRecipe(@RequestBody Recipe recipe) {
		if (recipe.getRecipeId() == null) {
			saveNewRecipe(recipe);
		} else {
			updateRecipe(recipe);
		}		
	}
	
	private void saveNewRecipe(Recipe recipe) {
		recipe.setDateCreated(LocalDateTime.now());
		String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userRepo.findByUsername(username).get();
		recipe.setUser(user);
		if (recipe.getCategory().getCategoryId().equals(-1L)) {
			recipe.setCategory(categoryRepo.findByName("None"));
		}
		replaceUnselectedUnits(recipe.getIngredients());
		recipeRepo.save(recipe);
		saveIngredients(recipe);
		
	}
	
	private void replaceUnselectedUnits(List<Ingredient> ingredients) {
		for (Ingredient ingredient : ingredients) {
			if (ingredient.getAmount().getUnit().getUnitId() == -1L) {
				ingredient.getAmount().setUnit(unitRepo.findByUnit("None"));
			}
		}
	}
	
	
	private void saveIngredients(Recipe recipe) {
		List<Ingredient> ingredients = recipe.getIngredients();
		for (Ingredient ingredient : ingredients) {
			ingredient.setRecipe(recipe);
			ingredientRepo.save(ingredient);
		}
	}
	
	private void updateRecipe(Recipe recipe) {
		recipe.setDateEdited(LocalDateTime.now());
		recipeRepo.save(recipe);
		saveIngredients(recipe);
	}
}
