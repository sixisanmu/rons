package com.demo.api.repository;

import com.demo.api.model.FoodFacility;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FoodFacilityRepository extends JpaRepository<FoodFacility, Long> {

    Optional<FoodFacility> findTop1ByLocationid(String locationid);

}
