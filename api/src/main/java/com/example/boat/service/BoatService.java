package com.example.boat.service;

import com.example.boat.model.entity.Boat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoatService {
	Boat findById(Long id);

	List<Boat> getBoats();

	Page<Boat> getBoats(String name, Pageable pageable);

	Boat updateBoat(Long id, Boat updatedBoat);

	Boat saveBoat(Boat boat);

	boolean deleteBoat(Long id);
}
