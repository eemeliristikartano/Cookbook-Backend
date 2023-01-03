package com.cookbook.cookbookdatabase.domain;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
	
	Optional<User> findByUsername(String username);

}
