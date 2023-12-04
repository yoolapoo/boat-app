package com.example.boat.controller;

import com.example.boat.mapper.MapperBoatToBoatDto;
import com.example.boat.model.dto.BoatDto;
import com.example.boat.model.entity.Boat;
import com.example.boat.service.BoatService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/boats")
public class BoatControllerImpl implements BoatController {

	private final BoatService service;

	private final MapperBoatToBoatDto mapper;

	public BoatControllerImpl(BoatService service, MapperBoatToBoatDto mapper) {
		this.service = service;
		this.mapper = mapper;
	}

	@Override public ResponseEntity<BoatDto> findById(long id) {
		Boat boat = service.findById(id);

		if (null != boat) {
			return ResponseEntity.ok(mapper.toBoatDto(boat));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Override public ResponseEntity<List<BoatDto>> findBoats() {
		return ResponseEntity.ok(mapper.toBoatDtoList(service.getBoats()));
	}

	@Override public ResponseEntity<Page<BoatDto>> filterBoats(String name,	Pageable pageable) {
		Page<Boat> boatPage = service.getBoats(name, pageable);
		return ResponseEntity.ok(mapper.toPageBoatDto(boatPage));
	}

	@Override public ResponseEntity<BoatDto> updateBoat(final Long id, final BoatDto updatedBoatDto) {
		Boat updatedBoat = mapper.toBoat(updatedBoatDto);
		Boat result = service.updateBoat(id, updatedBoat);
		return ResponseEntity.ok(mapper.toBoatDto(result));
	}

	@Override public ResponseEntity<BoatDto> createBoat(BoatDto boatDto) {
		Boat boat = mapper.toBoat(boatDto);
		Boat savedMedia = service.saveBoat(boat);
		return ResponseEntity.ok(mapper.toBoatDto(savedMedia));
	}

	@Override
	public ResponseEntity<Void> deleteBoat(Long id) {
		boolean deleted = service.deleteBoat(id);
		return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
	}
}
