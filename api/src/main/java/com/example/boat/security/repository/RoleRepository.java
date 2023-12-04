package com.example.boat.security.repository;

import com.example.boat.security.model.ERole;
import com.example.boat.security.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
		Optional<Role> findByName(ERole name);
		}
