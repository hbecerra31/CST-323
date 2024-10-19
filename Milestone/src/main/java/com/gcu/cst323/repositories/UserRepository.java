package com.gcu.cst323.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gcu.cst323.models.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Long> {
	Optional<UserModel> findByUsername(String username);

	boolean existsByUsername(String username);

	boolean existsByEmail(String email);
}
