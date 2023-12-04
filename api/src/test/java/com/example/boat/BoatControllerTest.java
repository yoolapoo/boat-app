package com.example.boat;

import com.example.boat.controller.BoatControllerImpl;
import com.example.boat.mapper.MapperBoatToBoatDto;
import com.example.boat.model.dto.BoatDto;
import com.example.boat.model.entity.Boat;
import com.example.boat.service.BoatService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BoatControllerTest {

	@Mock
	private BoatService boatService;

	@Mock
	private MapperBoatToBoatDto mapper;

	@InjectMocks
	private BoatControllerImpl boatController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Order(1)
	@Test
	void testCreateBoat() {
		BoatDto boatDto = new BoatDto();
		Boat boat = new Boat();

		when(mapper.toBoat(boatDto)).thenReturn(boat);
		when(boatService.saveBoat(boat)).thenReturn(boat);
		when(mapper.toBoatDto(boat)).thenReturn(boatDto);

		ResponseEntity<BoatDto> response = boatController.createBoat(boatDto);

		assertEquals(ResponseEntity.ok(boatDto), response);
		verify(mapper, times(1)).toBoat(boatDto);
		verify(boatService, times(1)).saveBoat(boat);
		verify(mapper, times(1)).toBoatDto(boat);
	}

	@Order(2)
	@Test
	void testFindById() {
		long boatId = 1L;
		Boat boat = new Boat();
		BoatDto boatDto = new BoatDto();

		when(boatService.findById(boatId)).thenReturn(boat);
		when(mapper.toBoatDto(boat)).thenReturn(boatDto);

		ResponseEntity<BoatDto> response = boatController.findById(boatId);

		assertEquals(ResponseEntity.ok(boatDto), response);
		verify(boatService, times(1)).findById(boatId);
		verify(mapper, times(1)).toBoatDto(boat);
	}

	@Order(3)
	@Test
	void testFindBoats() {
		Boat boat = new Boat();
		BoatDto boatDto = new BoatDto();

		when(boatService.getBoats()).thenReturn(Collections.singletonList(boat));
		when(mapper.toBoatDtoList(Collections.singletonList(boat))).thenReturn(Collections.singletonList(boatDto));

		ResponseEntity<List<BoatDto>> response = boatController.findBoats();

		assertEquals(ResponseEntity.ok(Collections.singletonList(boatDto)), response);
		verify(boatService, times(1)).getBoats();
		verify(mapper, times(1)).toBoatDtoList(Collections.singletonList(boat));
	}

	@Order(4)
	@Test
	void testFilterBoats() {
		String name = "boatName";
		Pageable pageable = Pageable.unpaged();
		Boat boat = new Boat();
		BoatDto boatDto = new BoatDto();

		Page<Boat> boatPage = new PageImpl<>(Collections.singletonList(boat));

		when(boatService.getBoats(name, pageable)).thenReturn(boatPage);
		when(mapper.toPageBoatDto(boatPage)).thenReturn(new PageImpl<>(Collections.singletonList(boatDto)));

		ResponseEntity<Page<BoatDto>> response = boatController.filterBoats(name, pageable);

		assertEquals(ResponseEntity.ok(new PageImpl<>(Collections.singletonList(boatDto))), response);
		verify(boatService, times(1)).getBoats(name, pageable);
		verify(mapper, times(1)).toPageBoatDto(boatPage);
	}

	@Order(5)
	@Test
	void testUpdateBoat() {
		Long boatId = 1L;
		BoatDto updatedBoatDto = new BoatDto();
		Boat updatedBoat = new Boat();

		when(mapper.toBoat(updatedBoatDto)).thenReturn(updatedBoat);
		when(boatService.updateBoat(boatId, updatedBoat)).thenReturn(updatedBoat);
		when(mapper.toBoatDto(updatedBoat)).thenReturn(updatedBoatDto);

		ResponseEntity<BoatDto> response = boatController.updateBoat(boatId, updatedBoatDto);

		assertEquals(ResponseEntity.ok(updatedBoatDto), response);
		verify(mapper, times(1)).toBoat(updatedBoatDto);
		verify(boatService, times(1)).updateBoat(boatId, updatedBoat);
		verify(mapper, times(1)).toBoatDto(updatedBoat);
	}

	@Order(6)
	@Test
	void deleteBoat() {
		Long boatId = 1L;

		when(boatService.deleteBoat(boatId)).thenReturn(true);

		ResponseEntity<Void> response = boatController.deleteBoat(boatId);

		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}

}
