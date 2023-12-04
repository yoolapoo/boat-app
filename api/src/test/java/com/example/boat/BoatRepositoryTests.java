package com.example.boat;

import com.example.boat.model.entity.Boat;
import com.example.boat.repository.BoatRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class BoatRepositoryTests {

	@Autowired
	private BoatRepository boatRepository;

	@Test
	void testFindAllByName() {
		// Given
		String boatName = "TestBoat";
		Boat boat = Boat.builder()
				.name(boatName).build();
		boatRepository.save(boat);
		Pageable page = Pageable.ofSize(1);

		// When
		Page<Boat> boats = boatRepository.findAllByName(boatName, page );

		// Then
		assertEquals(1, boats.stream().toList().size());
		assertEquals(boatName, boats.get().findFirst().isPresent() ? boats.get().findFirst().get().getName() : "");
	}

}
