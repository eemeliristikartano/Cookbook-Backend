package com.cookbook.cookbookdatabase.util;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
import com.cookbook.cookbookdatabase.domain.User;
import com.cookbook.cookbookdatabase.domain.UserRepository;

@Component
public class ResetDB {
	
	@Autowired
	private AmountRepository amountRepo;
	
	@Autowired
	private CategoryRepository categoryRepo; 
	
	@Autowired
	private IngredientRepository ingredientRepo;
	
	@Autowired
	private RecipeRepository recipeRepo; 
	
	@Autowired
	private UnitRepository unitRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	public void resetDB() {
		
		// Delete everything from database.
		userRepo.deleteAll();
		categoryRepo.deleteAll();
		unitRepo.deleteAll();
		ingredientRepo.deleteAll();
		recipeRepo.deleteAll();
		amountRepo.deleteAll();
		
		
		/*
		 * Generate new test data.
		 */
		
		List<String> instructions = List.of("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent efficitur tellus at odio laoreet congue. Aenean in purus hendrerit, auctor purus vel, ultrices sapien. Curabitur viverra lacus vel nulla euismod viverra. Quisque eu turpis eros. Nulla arcu erat, tincidunt vel iaculis sit amet, tempus interdum neque. Aenean volutpat sapien ac turpis accumsan interdum. Nunc tincidunt, nunc eget tristique vestibulum, urna sem condimentum arcu, quis sagittis mi sem at purus. Nam sagittis ut erat sit amet tempus. Interdum et malesuada fames ac ante ipsum primis in faucibus. Donec sit amet sapien vehicula elit bibendum tempus non ut mi. Mauris a augue vitae justo viverra cursus. Morbi ornare elit diam, quis elementum est consequat eu.", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent efficitur tellus at odio laoreet congue. Aenean in purus hendrerit, auctor purus vel, ultrices sapien. Curabitur viverra lacus vel nulla euismod viverra. Quisque eu turpis eros. Nulla arcu erat, tincidunt vel iaculis sit amet, tempus interdum neque. Aenean volutpat sapien ac turpis accumsan interdum. Nunc tincidunt, nunc eget tristique vestibulum, urna sem condimentum arcu, quis sagittis mi sem at purus. Nam sagittis ut erat sit amet tempus. Interdum et malesuada fames ac ante ipsum primis in faucibus. Donec sit amet sapien vehicula elit bibendum tempus non ut mi. Mauris a augue vitae justo viverra cursus. Morbi ornare elit diam, quis elementum est consequat eu.\n"
				+ "\n"
				+ "Pellentesque a sagittis nibh, elementum gravida augue. In nec pellentesque felis. In placerat ligula dolor. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Pellentesque lacinia dapibus massa et pellentesque. Vivamus dignissim maximus pellentesque. Mauris ultrices ligula gravida quam efficitur, non porta tortor pulvinar. Morbi convallis accumsan iaculis.", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent efficitur tellus at odio laoreet congue. Aenean in purus hendrerit, auctor purus vel, ultrices sapien. Curabitur viverra lacus vel nulla euismod viverra. Quisque eu turpis eros. Nulla arcu erat, tincidunt vel iaculis sit amet, tempus interdum neque. Aenean volutpat sapien ac turpis accumsan interdum. Nunc tincidunt, nunc eget tristique vestibulum, urna sem condimentum arcu, quis sagittis mi sem at purus. Nam sagittis ut erat sit amet tempus. Interdum et malesuada fames ac ante ipsum primis in faucibus. Donec sit amet sapien vehicula elit bibendum tempus non ut mi. Mauris a augue vitae justo viverra cursus. Morbi ornare elit diam, quis elementum est consequat eu.\n"
						+ "\n"
						+ "Pellentesque a sagittis nibh, elementum gravida augue. In nec pellentesque felis. In placerat ligula dolor. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Pellentesque lacinia dapibus massa et pellentesque. Vivamus dignissim maximus pellentesque. Mauris ultrices ligula gravida quam efficitur, non porta tortor pulvinar. Morbi convallis accumsan iaculis.\n"
						+ "\n"
						+ "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin bibendum bibendum tortor, vitae rutrum massa mollis eu. Sed sit amet suscipit nisi. Aenean ac magna et nisi molestie ultricies. Nullam volutpat ipsum et sapien sollicitudin, sed scelerisque quam finibus. Donec eget enim et tortor molestie commodo vitae id nisi. Phasellus ac dolor tincidunt, molestie tortor eget, tristique neque. Cras tortor leo, tristique laoreet porta ut, dignissim a ipsum.");
		List<String> ingredients = List.of("Carrot", "Meat", "Potato", "Rice", "Onion", "Pork");
		List<String> amounts = List.of("1", "2", "500", "3", "400", "800", "1/2");
		
		//Test users
		User userAdam = new User("Adam", "$2a$10$HDlTnxavfJyYM1ymWHf9wOAPMYymJK2CyJXeG18SF.Xpt2/GwvyBy", "USER");
		User userJohn = new User("John", "$2a$10$HDlTnxavfJyYM1ymWHf9wOAPMYymJK2CyJXeG18SF.Xpt2/GwvyBy", "USER");
		User userMike = new User("Mike", "$2a$10$HDlTnxavfJyYM1ymWHf9wOAPMYymJK2CyJXeG18SF.Xpt2/GwvyBy", "USER");
		User userOlivia = new User("Olivia", "$2a$10$HDlTnxavfJyYM1ymWHf9wOAPMYymJK2CyJXeG18SF.Xpt2/GwvyBy", "USER");
		User userEmma = new User("Emma", "$2a$10$HDlTnxavfJyYM1ymWHf9wOAPMYymJK2CyJXeG18SF.Xpt2/GwvyBy", "USER");
		User userAva = new User("Ava", "$2a$10$HDlTnxavfJyYM1ymWHf9wOAPMYymJK2CyJXeG18SF.Xpt2/GwvyBy", "USER");
		List<User> users = List.of(userAdam, userJohn, userMike, userOlivia, userEmma, userAva);
		userRepo.saveAll(users);
		
		//Categories
		Category vegan = new Category("Vegan");
		Category vegetarian = new Category("Vegetarian");
		Category meat = new Category("Meat");
		Category emptyCategory = new Category("None");
		List<Category> categories = List.of(vegan, vegetarian, meat, emptyCategory);
		categoryRepo.saveAll(categories);
		
		//Units
		Unit dl = new Unit("dl");
		Unit tl = new Unit("tl");
		Unit l = new Unit("l");
		Unit g = new Unit("g");
		Unit emptyUnit = new Unit("None");
		List<Unit> units = List.of(dl, tl, l, g, emptyUnit);
		unitRepo.saveAll(units);
					
		//Generating test data with for loops.
		Random rand = new Random();
		for (int i = 0; i < 20; i++) {
			String recipeName = "exampleRecipe" + Integer.toString(i + 1);
			//                                         Random instruction from instructions list.            Random created date.                              Random edited date.                                             Random user from users list.               Random category from categories list.
			Recipe recipe = new Recipe(recipeName, instructions.get(rand.nextInt(instructions.size() - 1)), LocalDateTime.now().minusDays(rand.nextLong(30)), LocalDateTime.now().plusDays(rand.nextLong(30)), "example.com", users.get(rand.nextInt(users.size() - 1)), categories.get(rand.nextInt(categories.size() - 1)));
			recipeRepo.save(recipe);
			//Random amount of ingredients per recipe.
			for (int y = 0; y < rand.nextInt(30); y++) {
				//                                       Random ingredient from ingredients list
				Ingredient ingredient = new Ingredient(ingredients.get(rand.nextInt(ingredients.size() - 1)), null, recipe);
				ingredientRepo.save(ingredient);
				//                          Random amount from amounts list.               Random unit from units list.
				Amount amount = new Amount(amounts.get(rand.nextInt(amounts.size() - 1)), units.get(rand.nextInt(units.size() - 1)));
				amountRepo.save(amount);
				ingredient.setAmount(amount);
				ingredientRepo.save(ingredient);
			}
		}
		
	}

}
