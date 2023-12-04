package com.example.boat.security.controller;

import com.example.boat.security.jwt.JwtUtils;
import com.example.boat.security.model.Role;
import com.example.boat.security.model.User;
import com.example.boat.security.payload.request.LoginRequest;
import com.example.boat.security.payload.request.SignupRequest;
import com.example.boat.security.payload.response.JwtResponse;
import com.example.boat.security.payload.response.MessageResponse;
import com.example.boat.security.repository.RoleRepository;
import com.example.boat.security.repository.UserRepository;
import com.example.boat.security.service.UserDetailsImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.example.boat.security.model.ERole.*;

@CrossOrigin(origins = "*", maxAge = 3600) @RestController @RequestMapping("/api/auth") public class AuthController {
	final AuthenticationManager authenticationManager;

	final UserRepository userRepository;

	final RoleRepository roleRepository;

	final PasswordEncoder encoder;

	final JwtUtils jwtUtils;

	public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository,
			PasswordEncoder encoder, JwtUtils jwtUtils) {
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.encoder = encoder;
		this.jwtUtils = jwtUtils;
	}

	@PostMapping("/signin") public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));
	}

	@PostMapping("/signup") public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (Boolean.TRUE.equals(userRepository.existsByUsername(signUpRequest.getUsername()))) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}

		if (Boolean.TRUE.equals(userRepository.existsByEmail(signUpRequest.getEmail()))) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = User.builder().username(signUpRequest.getUsername()).email(signUpRequest.getEmail())
				.password(encoder.encode(signUpRequest.getPassword())).build();

		Set<String> strRoles = signUpRequest.getRoles();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ROLE_USER).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin" -> {
					Role adminRole = roleRepository.findByName(ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);
				}
				case "mod" -> {
					Role modRole = roleRepository.findByName(ROLE_MODERATOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);
				}
				default -> {
					Role userRole = roleRepository.findByName(ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}
