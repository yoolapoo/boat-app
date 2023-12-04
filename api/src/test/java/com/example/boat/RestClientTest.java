package com.example.boat;

import com.example.boat.model.dto.BoatDto;
import org.junit.jupiter.api.*;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class) class RestClientTest {

	private static RestClient restClient;

	@BeforeAll
	static void setUp() {
		restClient = RestClient.builder()
				.baseUrl("http://localhost:8090/api/boats/")
				.build();
	}

	@Order(1)
	@Test void createBoat() {
		BoatDto newBoat = BoatDto.builder().name("toto").description("description").build();

		BoatDto savedBoat = restClient.post()
				.uri("create")
				.contentType(APPLICATION_JSON)
				.body(newBoat)
				.retrieve()
				.body(BoatDto.class);

		assertNotNull(savedBoat);
		assertEquals("toto", savedBoat.getName());
		assertEquals("description", savedBoat.getDescription());
	}

	@Order(2)
	@Test void getBoatById() {

		Long id = 1L;

		BoatDto boatDto = restClient.get()
				.uri("{id}", id)
				.retrieve()
				.body(BoatDto.class);

		assertNotNull(boatDto);
		assertEquals("toto", boatDto.getName());
		assertEquals("description", boatDto.getDescription());
	}

	@Order(3)
	@Test void updateBoat() {

		Long id = 1L;

		BoatDto updatedBoat = BoatDto.builder()
				.name("tata")
				.description("description2").build();

		BoatDto result = restClient.put()
				.uri("{id}", id)
				.contentType(APPLICATION_JSON)
				.body(updatedBoat)
				.retrieve()
				.body(BoatDto.class);

		assertNotNull(result);
		assertEquals(updatedBoat.getName(), result.getName());
		assertEquals(updatedBoat.getDescription(), result.getDescription());
	}

	@Order(4)
	@Test void findAll() {
		List<BoatDto> list = restClient.get()
				.uri("")
				.retrieve()
				.body(new ParameterizedTypeReference<>() {
				});
		assertNotNull(list);
		assertFalse(list.isEmpty());

		list.forEach(boatDto -> assertNotNull(boatDto.getName()));

	}

	@Order(5)
	@Test void deleteBoat() {
		Long id = 1L;

		String response = restClient.delete()
				.uri("delete/{id}", id)
				.retrieve()
				.body(String.class);

		assertNotNull(response);
		assertEquals("Boat deleted successfully", response);
	}
}
