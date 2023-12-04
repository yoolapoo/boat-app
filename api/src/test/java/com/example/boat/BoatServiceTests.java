package com.example.boat;

import com.example.boat.model.entity.Boat;
import com.example.boat.repository.BoatRepository;
import com.example.boat.service.BoatServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BoatServiceTests {

	@Mock
	private BoatRepository boatRepository;

	@InjectMocks
	private BoatServiceImpl boatService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFindById() {
		long boatId = 1L;
		Boat boat = new Boat();

		when(boatRepository.findById(boatId)).thenReturn(Optional.of(boat));

		Boat result = boatService.findById(boatId);

		assertEquals(boat, result);
		verify(boatRepository, times(1)).findById(boatId);
	}

	@Test
	void testGetBoats() {
		Boat boat = new Boat();

		when(boatRepository.findAll()).thenReturn(Collections.singletonList(boat));

		assertEquals(Collections.singletonList(boat), boatService.getBoats());
		verify(boatRepository, times(1)).findAll();
	}

	@Test
	void deleteBoat() {
		Long boatId = 1L;

		when(boatRepository.existsById(boatId)).thenReturn(true);

		boolean result = boatService.deleteBoat(boatId);

		Assertions.assertTrue(result);
		verify(boatRepository, times(1)).deleteById(boatId);
	}
}

