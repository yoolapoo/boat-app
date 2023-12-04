package com.example.boat;

import com.example.boat.model.dto.BoatDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class) class RestClientTest {

	private final RestClient restClient;

	public RestClientTest() {
		restClient = RestClient.builder()
				.baseUrl("http://localhost:8090")
				.build();
	}

	@Order(1)
	@Test void createBoat() {
		BoatDto newEmployee = BoatDto.builder().name("toto").description("description").build();

		BoatDto savedEmployee = restClient.post()
				.uri("/api/boats")
				.contentType(APPLICATION_JSON)
				.body(newEmployee)
				.retrieve()
				.body(BoatDto.class);

		System.out.println(savedEmployee.toString());
	}

	@Order(2)
	@Test void getBoatById() {

		Long id = 1L;

		BoatDto BoatDto = restClient.get()
				.uri("/api/boats/{id}", id)
				.retrieve()
				.body(BoatDto.class);

		System.out.println(BoatDto);
	}

	@Order(3)
	@Test void updateBoat() {

		Long id = 1L;

		BoatDto updatedBoat = BoatDto.builder()
				.name("tata")
				.description("description2").build();

		BoatDto result = restClient.put()
				.uri("/api/boats/{id}", id)
				.contentType(APPLICATION_JSON)
				.body(updatedBoat)
				.retrieve()
				.body(BoatDto.class);

		System.out.println(result.toString());
	}

	@Order(4)
	@Test void findAll() {
		List<BoatDto> list = restClient.get()
				.uri("/api/boats")
				.retrieve()
				.body(new ParameterizedTypeReference<>() {
				});

		list.forEach(BoatDto -> {
			System.out.println(BoatDto.toString());
		});
	}

	@Order(5)
	@Test void deleteBoat() {
		Long id = 1L;

		String response = restClient.delete()
				.uri("/api/boats/{id}", id)
				.retrieve()
				.body(String.class);

		System.out.println(response);
	}
}
