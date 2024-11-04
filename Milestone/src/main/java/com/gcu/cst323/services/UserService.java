// Triggering CI/CD pipeline test
package com.gcu.cst323.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gcu.cst323.models.UserModel;
import com.gcu.cst323.repositories.UserRepository;

/**
 * UserService is a service class that provides user-related operations.
 */
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * Registers a new user.
	 *
	 * @param user the UserModel object containing the user data
	 * @return the registered UserModel object
	 * @throws RuntimeException if the username or email is already taken
	 */
	public UserModel registerUser(UserModel user) {
		// Check if the username is already taken
		if (userRepository.existsByUsername(user.getUsername())) {
			throw new RuntimeException("Username is already taken.");
		}
		// Check if the email is already registered
		if (userRepository.existsByEmail(user.getEmail())) {
			throw new RuntimeException("Email is already registered.");
		}
		user.setPassword(passwordEncoder.encode(user.getPassword())); // Hash the password
		return userRepository.save(user);
	}

	/**
	 * Retrieves all users.
	 *
	 * @return a list of all UserModel objects
	 */
	public List<UserModel> getAllUsers() {
		return userRepository.findAll();
	}

	/**
	 * Finds a user by their ID.
	 *
	 * @param id the ID of the user to be found
	 * @return an Optional containing the found UserModel object, or empty if not
	 *         found
	 */
	public Optional<UserModel> findUserById(Long id) {
		return userRepository.findById(id);
	}

	/**
	 * Finds a user by their username.
	 *
	 * @param username the username of the user to be found
	 * @return an Optional containing the found UserModel object, or empty if not
	 *         found
	 */
	public Optional<UserModel> findUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	/**
	 * Checks if the raw password matches the hashed password.
	 *
	 * @param rawPassword    the raw password to be checked
	 * @param hashedPassword the hashed password to be checked against
	 * @return true if the passwords match, false otherwise
	 */
	public boolean checkPassword(String rawPassword, String hashedPassword) {
		return passwordEncoder.matches(rawPassword, hashedPassword);
	}
}
