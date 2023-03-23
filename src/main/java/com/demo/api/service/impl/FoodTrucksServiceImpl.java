package com.demo.api.service.impl;

import com.csvreader.CsvReader;
import com.demo.api.model.FoodFacility;
import com.demo.api.model.FoodFacilityMini;
import com.demo.api.repository.FoodFacilityMiniRepository;
import com.demo.api.repository.FoodFacilityRepository;
import com.demo.api.response.FoodTrucks;
import com.demo.api.service.FoodTrucksService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class FoodTrucksServiceImpl implements FoodTrucksService {
    @Autowired
    private FoodFacilityMiniRepository foodFacilityMiniRepository;

    @Autowired
    private FoodFacilityRepository foodFacilityRepository;

    @Autowired
    private RedisTemplate redisTemplate;



    @Override
    public List<FoodFacilityMini>  findFoodTrucks(String food) {

        return foodFacilityMiniRepository.findByFoodItemsLike( "%"+food+"%");
    }

    @Override
    public List<FoodFacilityMini> findFoodTrucksCache(String food) {

        ObjectMapper objectMapper = new ObjectMapper();
        List<FoodFacilityMini> list =  new ArrayList<>();
        String cacheKey = "foodtrucks:" + food;

        String strCache = (String) redisTemplate.boundValueOps(cacheKey).get();
        if(strCache != null ) {

            try {
                list = objectMapper.readValue(strCache, new TypeReference<List<FoodFacilityMini>>(){});
            } catch (JsonProcessingException e) {

            }

        }else {

            list = findFoodTrucks(food);
            String jsonStr = new String("");
            try {
                jsonStr = objectMapper.writeValueAsString(list);
            } catch (JsonProcessingException e) {

            }

            redisTemplate.boundValueOps(cacheKey).set(jsonStr,1, TimeUnit.MINUTES);

        }

        return list;

    }



    @Override
    public List<FoodFacility>  setDataToDb() {
        CsvReader reader = null;
        List<FoodFacility> csvs = new ArrayList<>();
        try {
            //urlExcel  csv文件远程地址
            String urlExcel = "https://data.sfgov.org/api/views/rqzj-sfat/rows.csv";
            URL url = new URL(urlExcel);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            DataInputStream inputStream = new DataInputStream(connection.getInputStream());
            reader = new CsvReader(inputStream, ',', Charset.forName("GBK"));
            reader.readHeaders();
            String lineTxt = null;
            while (reader.readRecord()) {
                String[] columns = null;

                //                String regstr = "(\\()([-+]?)([\\d]{1,2})(((\\.)(\\d+)(,)))(\\s*)(([-+]?)([\\d]{1,3})((\\.)(\\d+))?(\\)))";
                String lineTxtRow = reader.getRawRecord();

                lineTxtRow = lineTxtRow.replace(", ", " ");
                //                Pattern pattern = Pattern.compile(regstr); //去掉空格符合换行符
                //                Matcher matcher = pattern.matcher(lineTxtRow);
                //                lineTxt = matcher.replaceAll("");

                columns = lineTxtRow.split(",");

                FoodFacility facility =  setData(columns);

                Optional<FoodFacility> foodFacilityOptional =  foodFacilityRepository.findTop1ByLocationid(facility.getLocationid());
                if(foodFacilityOptional.isPresent()){

                    facility.setId(foodFacilityOptional.get().getId());
                }
                // save data
                csvs.add(facility);

                // foodFacilityRepository.save(facility);

                // 异步线程执行
                CompletableFuture.supplyAsync(() -> foodFacilityRepository.save(facility));

            }

            reader.close();
            inputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());

        }
        return csvs;

    }

    private double rad(double d) {
        return d * Math.PI / 180.0;
    }

    @Override
    public double calcDidtance(double lat1, double lng1, double lat2, double lng2) {

        double EARTH_RADIUS = 6378.137;

        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(
                Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = (s * 10000) / 10;
        return s;

    }

    @Override
    public List<FoodTrucks> findFoodTrucksResult(List<FoodFacilityMini> foodFacilityMinis, String latlng) {
        latlng = latlng + ",0";
        String[] latarr = latlng.split(",");
        String latstr = latarr[0];
        String lngstr = latarr[1];
        double lat1 = Double.valueOf(latstr);
        double lng1 = Double.valueOf(lngstr);

        List<FoodTrucks> foodTrucks = new ArrayList<>();
        for (FoodFacilityMini one : foodFacilityMinis) {
            double lat2 = 0.0;
            double lng2 = 0.0;
            try {
                 lat2 = Double.valueOf(one.getLatitude());
                 lng2 = Double.valueOf(one.getLongitude());
            } catch (NumberFormatException e) {

            }

            FoodTrucks foodTrucks1 = new FoodTrucks();
            double distince = calcDidtance(lat1, lng1, lat2, lng2);

            foodTrucks1.setDistince(new Double(distince).longValue());
            foodTrucks1.setId(one.getId());
            foodTrucks1.setLocationid(one.getLocationid());
            foodTrucks1.setCnn(one.getCnn());
            foodTrucks1.setFacilityType(one.getFacilityType());
            foodTrucks1.setAddress(one.getAddress());
            foodTrucks1.setApplicant(one.getApplicant());
            foodTrucks1.setFoodItems(one.getFoodItems());

            foodTrucks.add(foodTrucks1);
        }

        // sort by distance
        List<FoodTrucks> foodTrucks1 = foodTrucks.stream().
                sorted((Comparator.comparingLong(FoodTrucks::getDistince)))
                .collect(Collectors.toList());


        return foodTrucks1;
    }


    private FoodFacility setData(String[] rowItem ){
        int leng = rowItem != null ?  rowItem.length : 0;
        // 防止数组越界
        String[] columns = new String[29];
        for (int i = 0; i < 29; i++) {
            columns[i] = leng > i ? rowItem[i] : "";
        }
        FoodFacility foodTrucks = new FoodFacility();
        foodTrucks.setLocationid((columns[0]));
        foodTrucks.setApplicant(columns[1]);
        foodTrucks.setFacilityType(columns[2]);
        foodTrucks.setCnn((columns[3]));
        foodTrucks.setLocationDescription(columns[4]);
        foodTrucks.setAddress(columns[5]);
        foodTrucks.setBlocklot(columns[6]);
        foodTrucks.setBlock(columns[7]);
        foodTrucks.setLot(columns[8]);
        foodTrucks.setPermit(columns[9]);
        foodTrucks.setStatus(columns[10]);
        foodTrucks.setFoodItems(columns[11]);
        foodTrucks.setX((columns[12]));
        foodTrucks.setY((columns[13]));
        foodTrucks.setLatitude((columns[14]));
        foodTrucks.setLongitude((columns[15]));
        foodTrucks.setSchedule(columns[16]);
        foodTrucks.setDayshours(columns[17]);
        foodTrucks.setNoiSent(columns[18]);
        foodTrucks.setApproved(columns[19]);
        foodTrucks.setReceived(columns[20]);
        foodTrucks.setPriorPermit(columns[21]);
        foodTrucks.setExpirationDate(columns[22]);
        foodTrucks.setLocation(columns[23]);
        foodTrucks.setFirePreventionDistricts(columns[24]);
        foodTrucks.setPoliceDistricts(columns[25]);
        foodTrucks.setSupervisorDistricts(columns[26]);
        foodTrucks.setZipCodes(columns[27]);
        foodTrucks.setNeighborhoodsOld(columns[28]);


        return foodTrucks;
    }


}
