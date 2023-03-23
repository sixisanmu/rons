package com.demo.api.repository;

import com.demo.api.model.FoodFacilityMini;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FoodFacilityMiniRepository extends JpaRepository<FoodFacilityMini, Long> {

//    @Query("select u from FoodFacilityMini u where u.foodItems like '%?1%'")
//    List<FoodFacilityMini> findByFoodName(String name);

    List<FoodFacilityMini> findByFoodItemsLike(String name);

}
