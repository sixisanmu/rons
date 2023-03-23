package com.demo.api.job;

import com.demo.api.service.FoodTrucksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class InitJob {

    @Autowired
    FoodTrucksService foodTrucksService;
    /**
     * 第一次启动时拉取，后续半小时更新一次
     */
    @Scheduled(initialDelay = 1000, fixedRate = 1800000)
        public void setDataToDb(){

            foodTrucksService.setDataToDb();
            System.out.println("food trucks data updated  success");

        }
    
}
