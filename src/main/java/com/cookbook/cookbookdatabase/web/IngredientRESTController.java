package com.cookbook.cookbookdatabase.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cookbook.cookbookdatabase.domain.IngredientRepository;

@CrossOrigin
@RestController
public class IngredientRESTController {
	
	@Autowired
	private IngredientRepository ingredientRepo;
	
	@PostMapping(value = "/deleteingredient")
	public @ResponseBody void deleteIngredient(@RequestBody Long ingredientId) {
		ingredientRepo.deleteById(ingredientId);
	}

}
