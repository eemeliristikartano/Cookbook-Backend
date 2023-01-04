package com.cookbook.cookbookdatabase;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.cookbook.cookbookdatabase.domain.User;
import com.cookbook.cookbookdatabase.domain.UserRepository;


/*
 * Testing saving a new user, deleting an existing user and updating an existing user.
 */

@DataJpaTest
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository repo;
	
	@Test
	void saveUser() {
		repo.save(new User("Testing", "password", "USER"));
		assertThat(repo.findByUsername("Testing").isPresent()).isTrue();
	}
	
	@Test
	void deleteUser() {
		// Save a new user.
		repo.save(new User("Testing", "password", "USER"));
		Optional<User> user = repo.findByUsername("Testing");
		// Delete the user.
		repo.delete(user.get());
		// Assert that the user is not present
		assertThat(repo.findByUsername("Testing").isEmpty()).isTrue();
	}
	
	@Test
	void updateUser() {
		// Save a new user.
		repo.save(new User("Testing", "password", "USER"));
		// Find the user by name.
		User user = repo.findByUsername("Testing").get();
		// Update the user
		user.setUsername("TestingUpdate");
		repo.save(user);
		// Assert that the updated user is found.
		assertThat(repo.findByUsername("TestingUpdate").isPresent()).isTrue();
		
	}


}
