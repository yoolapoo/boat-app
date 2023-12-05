package com.example.boat.controller;

import com.example.boat.model.dto.BoatDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface BoatController {
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	ResponseEntity<BoatDto> findById(@PathVariable long id);

	@GetMapping("/")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	ResponseEntity<List<BoatDto>> findBoats();

	@GetMapping("/filter")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	ResponseEntity<Page<BoatDto>> filterBoats(
			@RequestParam(name = "name", required = false) String name,
			@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable);

	@PutMapping("/update/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	@ResponseStatus(HttpStatus.OK) ResponseEntity<BoatDto> updateBoat(@PathVariable final Long id,
			@RequestBody final BoatDto updatedBoatDto);

	@PostMapping("/create")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	ResponseEntity<BoatDto> createBoat(@RequestBody BoatDto boatDto);

	@DeleteMapping("/delete/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	ResponseEntity<Void> deleteBoat(@PathVariable Long id);
}
