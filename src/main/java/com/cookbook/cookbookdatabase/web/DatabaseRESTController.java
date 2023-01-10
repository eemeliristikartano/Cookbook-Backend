package com.cookbook.cookbookdatabase.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cookbook.cookbookdatabase.util.ResetDB;

@CrossOrigin
@RestController
public class DatabaseRESTController {
	
	@Autowired
	private ResetDB resetDB;
	
	@PostMapping(value = "/reset")
	public ResponseEntity<String> resetDB() {
		resetDB.resetDB();
		return ResponseEntity.ok("Reset done");
	}

}
