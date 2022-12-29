package com.cookbook.cookbookdatabase.web;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cookbook.cookbookdatabase.domain.Amount;
import com.cookbook.cookbookdatabase.domain.AmountRepository;
import com.cookbook.cookbookdatabase.domain.Category;
import com.cookbook.cookbookdatabase.domain.CategoryRepository;
import com.cookbook.cookbookdatabase.domain.Ingredient;
import com.cookbook.cookbookdatabase.domain.IngredientRepository;
import com.cookbook.cookbookdatabase.domain.Recipe;
import com.cookbook.cookbookdatabase.domain.RecipeRepository;
import com.cookbook.cookbookdatabase.domain.Unit;
import com.cookbook.cookbookdatabase.domain.UnitRepository;
import com.cookbook.cookbookdatabase.domain.UserRepository;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

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
	
	/*
	 * Method returns all recipes from database.
	 */
	
	@GetMapping(value = "/recipes")
	public @ResponseBody Iterable<Recipe> getRecipes() {
		return recipeRepo.findAll();
	}
	
	/*
	 * This method is used for saving a new recipe.
	 */
	
	@PostMapping(value = "/saverecipe")
	public @ResponseBody void saveNewRecipe(@RequestBody String recipeFromFront) {
		Random rand = new Random();
		try {
			//Parses jsonobject from a string.
			JsonObject jsonRecipe = JsonParser.parseString(recipeFromFront).getAsJsonObject();
			Category category = null;
	
			//Empty object for recipe.
			Recipe recipe = new Recipe();
			//Using setters to set values to the new recipe.
			//:TODO User is randomized for now. Later it should be a real user from a client side.
			recipe.setUser(userRepo.findById(rand.nextLong(5)).get());
			recipe.setRecipeName(jsonRecipe.get("recipeName").getAsString());
			
			recipe.setInstructions(jsonRecipe.get("instructions").getAsString());
			recipe.setSource(jsonRecipe.get("source").getAsString());
			recipe.setDateCreated(LocalDateTime.now());
			
			//:TODO There should be an empty value in database for an unit to prevent null-values.
			
			// Checking if the category is an object. 
			if (jsonRecipe.get("category").isJsonObject()) {
				// Checking if a categoryId does not equal -1L.
				if (jsonRecipe.get("category").getAsJsonObject().get("categoryId").getAsLong() != -1L) {
					// Find category by categoryId.
					category = categoryRepo.findById(jsonRecipe.get("category").getAsJsonObject().get("categoryId").getAsLong()).get();
				} else {
					// Else set empty value from database for category.
					category = categoryRepo.findByName("None");
				}
			} else {
				// Else set empty value from database for category.
				category = categoryRepo.findByName("None");
			}
			
			recipe.setCategory(category);
			
			recipeRepo.save(recipe);		
			//System.err.println(jsonRecipe.get("recipeName").getAsString());
			JsonArray ingredients = jsonRecipe.get("ingredients").getAsJsonArray();
			// Looping through the ingredients.
			for (int i = 0; i < ingredients.size(); i++) {
				Ingredient ingredient = new Ingredient();
				Amount amount = new Amount();
				Unit unit = null;
				//Gets ingredient from array
				String ingredientFromJson = ingredients.get(i).getAsJsonObject().get("ingredientName").getAsString();
				//Gets amount from array
				String quantityFromJson = ingredients.get(i).getAsJsonObject().get("amount").getAsJsonObject().get("quantity").getAsString();
				
				// Checking if the unit is an object. 
				if (ingredients.get(i).getAsJsonObject().get("amount").getAsJsonObject().get("unit").isJsonObject()) {
					// Checking if a unitId does not equal -1L.
					if (ingredients.get(i).getAsJsonObject().get("amount").getAsJsonObject().get("unit").getAsJsonObject().get("unitId").getAsLong() != -1L) {
						// Find category by unitId.
						unit = unitRepo.findById(ingredients.get(i).getAsJsonObject().get("amount").getAsJsonObject().get("unit").getAsJsonObject().get("unitId").getAsLong()).get();
					} else {
						// Else set empty value from database for unit.
						unit = unitRepo.findByUnit("None");
					}
				} else {
					// Else set empty value from database for unit.
					unit = unitRepo.findByUnit("None");
				}

				
				// Checking if values are blank. If they're blank, there is no need to save them.
				if (ingredientFromJson.isBlank() && quantityFromJson.isBlank()) {
					continue;					
				} 
				
				// Using setters so set values for the amount.
				amount.setQuantity(quantityFromJson);
				amount.setUnit(unit);
		
				amountRepo.save(amount);
				
				// Using setters to set values for the ingredient.
				ingredient.setIngredientName(ingredientFromJson);
				ingredient.setAmount(amount);
				// Earlier saved recipe for the new ingredient.
				ingredient.setRecipe(recipe);
		
				ingredientRepo.save(ingredient);
			}
			
			
		} catch (JsonParseException e) {
			// TODO: handle exception
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@PostMapping(value = "/deleterecipe")
	public @ResponseBody void deleteRecipe(@RequestBody Long recipeId) {
		recipeRepo.deleteById(recipeId);
	}

}
