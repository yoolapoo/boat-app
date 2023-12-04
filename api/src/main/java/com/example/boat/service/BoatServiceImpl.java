package com.example.boat.service;

import com.example.boat.model.entity.Boat;
import com.example.boat.repository.BoatRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service public class BoatServiceImpl implements BoatService {

	private final BoatRepository repository;

	public BoatServiceImpl(BoatRepository repository) {
		this.repository = repository;
	}

	@Override public Boat findById(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override public List<Boat> getBoats() {
		return new ArrayList<>(repository.findAll());
	}

	@Override public Page<Boat> getBoats(String name, Pageable pageable) {
		return repository.findAllByName(name, pageable);
	}

	@Override public Boat updateBoat(Long id, Boat updatedBoat) {
		Boat existingMedia = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Boat not found with id: " + id));

		existingMedia.setName(updatedBoat.getName());
		existingMedia.setDescription(updatedBoat.getDescription());

		return repository.save(existingMedia);
	}

	@Override public Boat saveBoat(Boat boat) {
		return repository.save(boat);
	}

	@Override
	public boolean deleteBoat(Long id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
			return true;
		}
		return false;
	}
}
