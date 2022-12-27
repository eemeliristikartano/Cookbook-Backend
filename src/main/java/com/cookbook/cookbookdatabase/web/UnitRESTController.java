package com.cookbook.cookbookdatabase.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cookbook.cookbookdatabase.domain.Unit;
import com.cookbook.cookbookdatabase.domain.UnitRepository;

@RestController
@CrossOrigin
public class UnitRESTController {
	
	@Autowired
	private UnitRepository unitRepo;
	
	@GetMapping(value = "/units")
	public @ResponseBody Iterable<Unit> getUnits() {
		return unitRepo.findAll();
	}

}
