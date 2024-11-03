package com.gcu.cst323.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gcu.cst323.models.UserModel;

/**
 * UserRepository is a Spring Data JPA repository for managing UserModel
 * entities.
 */
public interface UserRepository extends JpaRepository<UserModel, Long> {
	/**
	 * Finds a user by username.
	 *
	 * @param username the username of the user to be found
	 * @return the user with the given username, if it exists
	 */
	Optional<UserModel> findById(Long id);

	/**
	 * Finds a user by username.
	 *
	 * @param username the username of the user to be found
	 * @return the user with the given username, if it exists
	 */
	Optional<UserModel> findByUsername(String username);

	/**
	 * Finds a user by email.
	 *
	 * @param email the email of the user to be found
	 * @return the user with the given email, if it exists
	 */
	boolean existsByUsername(String username);
	
	/**
	 * Finds a user by email.
	 *
	 * @param email the email of the user to be found
	 * @return the user with the given email
	 * @return the user with the given email, if it exists
	 */
	boolean existsByEmail(String email);
}
