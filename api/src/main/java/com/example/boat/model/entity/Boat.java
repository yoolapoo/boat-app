package com.example.boat.model.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "boats") @Data @NoArgsConstructor @AllArgsConstructor @SuperBuilder
public class Boat {
	@Id private Long id;
	@NotBlank private String name;

	private String description;
}
