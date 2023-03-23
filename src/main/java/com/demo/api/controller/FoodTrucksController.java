package com.demo.api.controller;


import com.demo.api.model.FoodFacility;
import com.demo.api.model.FoodFacilityMini;
import com.demo.api.response.FoodTrucks;
import com.demo.api.service.FoodTrucksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@CrossOrigin
public class FoodTrucksController {

    @Autowired
    FoodTrucksService foodTrucksService;

    /**
     *
     * @param food   the food name  user want to search
     * @param latlng   the
     * @return
     */
    @GetMapping("/food/trucks")
    public List<FoodTrucks> foodTrucks( @RequestParam(value = "food", required=false, defaultValue = "") String food,
                                        @RequestParam(value = "latlng", required=false, defaultValue = "0.0,0.0") String latlng ) throws Exception
    {
        latlng = latlng + ",0.0,0.0";
        String[] latlngarr = latlng.split(",");
        List<FoodTrucks>  foodTrucks = new ArrayList<>();

        // 这个代码意思是如果没有抛出异常 就证明是数字，抛出异常了那么就不是数字
        try {
            Double.parseDouble(latlngarr[0]);
            Double.parseDouble(latlngarr[1]);
        } catch (NumberFormatException e) {
            // 异常返回空
            return foodTrucks;
        }


        List<FoodFacilityMini> foodFacilityMinis =  foodTrucksService.findFoodTrucksCache(food);
        foodTrucks =   foodTrucksService.findFoodTrucksResult(foodFacilityMinis, latlng);
        return foodTrucks;
    }


    @GetMapping("/data/import")
    public List<FoodFacility>  importCsv( )
    {

        return  foodTrucksService.setDataToDb();


    }


    @GetMapping("/monitor/service")
    public String service()
    {

        return "success";
    }

    @GetMapping("/monitor/business")
    public String business()
    {

        return "success2222";
    }


}
