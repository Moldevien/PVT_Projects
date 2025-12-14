package com.lab8.repository;

import com.lab8.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
	Optional<Place> findByName(String name);
	//boolean existsByNameAndAddress(String name, String address);
}
