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
import com.cookbook.cookbookdatabase.domain.CategoryRepository;
import com.cookbook.cookbookdatabase.domain.Ingredient;
import com.cookbook.cookbookdatabase.domain.IngredientRepository;
import com.cookbook.cookbookdatabase.domain.Recipe;
import com.cookbook.cookbookdatabase.domain.RecipeRepository;
import com.cookbook.cookbookdatabase.domain.UnitRepository;
import com.cookbook.cookbookdatabase.domain.UserRepository;
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
			//Empty object for recipe.
			Recipe recipe = new Recipe();
			//Using setters to set values to the new recipe.
			//:TODO User is randomized for now. Later it should be a real user from client side.
			recipe.setUser(userRepo.findById(rand.nextLong(5)).get());
			recipe.setName(jsonRecipe.get("recipeName").getAsString());
			recipe.setInstructions(jsonRecipe.get("instructions").getAsString());
			recipe.setSource(jsonRecipe.get("source").getAsString());
			recipe.setDateCreated(LocalDateTime.now());
			if (jsonRecipe.get("category").getAsString().equals("None")) recipe.setCategory(null);
			else recipe.setCategory(categoryRepo.findByName(jsonRecipe.get("category").getAsString()));
			recipeRepo.save(recipe);		
			
			// Looping through the ingredients.
			for (int i = 0; i < jsonRecipe.get("ingredients").getAsJsonArray().size(); i++) {
				//Gets ingredient from array
				String ingredientFromJson = jsonRecipe.get("ingredients").getAsJsonArray().get(i).getAsJsonObject().get("ingredientName").getAsString();
				//Gets amount from array
				String quantityFromJson = jsonRecipe.get("ingredients").getAsJsonArray().get(i).getAsJsonObject().get("amount").getAsString();
				//Gets unit from array
				String unitFromJson = jsonRecipe.get("ingredients").getAsJsonArray().get(i).getAsJsonObject().get("unit").getAsString();
				
				// Checking if every value is blank. If they're blank, there is no need to save them.
				if (ingredientFromJson.isBlank() && quantityFromJson.isBlank() && (unitFromJson.isBlank() || unitFromJson.equals("None"))) {
					continue;					
				}
				
			
				Ingredient ingredient = new Ingredient();
				Amount amount = new Amount();
				
				// Using setters so set values for the amount.
				amount.setQuantity(quantityFromJson);
				amount.setUnit(unitRepo.findByUnit(unitFromJson));
		
				amountRepo.save(amount);
				
				// Using setters to set values for the ingredient.
				ingredient.setName(ingredientFromJson);
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

}
