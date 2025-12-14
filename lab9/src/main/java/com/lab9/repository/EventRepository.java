package com.lab9.repository;

import com.lab9.model.Event;
import com.lab9.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
	List<Event> findByName(String name);
	List<Event> findByEventDateAfter(LocalDate date); // майбутні
	boolean existsByPlaceAndEventDate(Place place, LocalDate date); // перевірка унікальності місця/дата
}
