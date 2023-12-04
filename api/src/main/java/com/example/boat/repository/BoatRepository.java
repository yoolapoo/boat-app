package com.example.boat.repository;

import com.example.boat.model.entity.Boat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository public interface BoatRepository extends MongoRepository<Boat, Long> {
	Page<Boat> findAllByName(String name, Pageable pageable);
}
