package com.demo.api.service;

import com.demo.api.model.FoodFacility;
import com.demo.api.model.FoodFacilityMini;
import com.demo.api.response.FoodTrucks;

import java.util.List;

public interface FoodTrucksService {

    List<FoodFacilityMini>  findFoodTrucks(String food);

    List<FoodFacilityMini>  findFoodTrucksCache(String food);

    List<FoodFacility>  setDataToDb();

    double  calcDidtance(double lat1, double lng1, double lat2, double lng2);

//    List<FoodTrucks> findFoodTrucksResult(List<FoodFacilityMini> foodFacilityMinis);
    List<FoodTrucks> findFoodTrucksResult(List<FoodFacilityMini> foodFacilityMinis, String latlng);
}
