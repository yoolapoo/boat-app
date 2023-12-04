package com.example.boat.mapper;

import com.example.boat.model.dto.BoatDto;
import com.example.boat.model.entity.Boat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component public class MapperBoatToBoatDto {

	public Page<BoatDto> toPageBoatDto(Page<Boat> page) {
		List<BoatDto> mediaDtoList = page.getContent().stream().map(this::toBoatDto).toList();
		return new PageImpl<>(mediaDtoList, page.getPageable(), page.getTotalElements());
	}

	public List<BoatDto> toBoatDtoList(List<Boat> list) {
		return list.stream()
				.map(this::toBoatDto)
				.collect(Collectors.toList());
	}

	public Boat toBoat(BoatDto boatDto) {
		return Boat.builder()
				.id(boatDto.getId())
				.name(boatDto.getName())
				.description(boatDto.getDescription())
				.build();
	}

	public BoatDto toBoatDto(Boat boat) {
		if (boat == null) {
			return null;
		}
		return BoatDto.builder()
				.id(boat.getId())
				.name(boat.getName())
				.description(boat.getDescription())
				.build();
	}
}
