package com.cookbook.cookbookdatabase.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cookbook.cookbookdatabase.domain.Category;
import com.cookbook.cookbookdatabase.domain.CategoryRepository;

@RestController
@CrossOrigin
public class CategoryRESTController {
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	@GetMapping(value = "/categories")
	public @ResponseBody Iterable<Category> getCategories() {
		return categoryRepo.findAll();
	}

}
