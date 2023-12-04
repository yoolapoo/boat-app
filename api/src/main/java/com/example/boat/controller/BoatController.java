package com.example.boat.controller;

import com.example.boat.model.dto.BoatDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface BoatController {
	@GetMapping("/{id}") ResponseEntity<BoatDto> findById(@PathVariable long id);

	@GetMapping("/") ResponseEntity<List<BoatDto>> findBoats();

	@GetMapping("/filter") ResponseEntity<Page<BoatDto>> filterBoats(
			@RequestParam(name = "name", required = false) String name,
			@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable);

	@PutMapping("/update/{id}")
	@ResponseStatus(HttpStatus.OK) ResponseEntity<BoatDto> updateBoat(@PathVariable final Long id,
			@RequestBody final BoatDto updatedBoatDto);

	@PostMapping("/create") ResponseEntity<BoatDto> createBoat(@RequestBody BoatDto boatDto);

	@DeleteMapping("/delete/{id}")
	ResponseEntity<Void> deleteBoat(@PathVariable Long id);
}
