## environment
```
   Server side: mysql5.7  JDK11  springboot 2.7.3   redis      
   Front side: javascript  jquery   bootStrap 
```


## create DB , config application.properties
- first you only need create a empty database  `test` ,the table and it's data will auto initialization by program `com.demo.api.job.InitJob.setDataToDb` 
- then set the datasource config like this 
```
  spring.datasource.url=jdbc:mysql://127.0.0.1:3306/test?useSSL=false
```

- set the redis config

```
    spring.redis.database=0
    spring.redis.host=127.0.0.1
    spring.redis.port=6379
    spring.redis.password=
    spring.redis.jedis.pool.max-active=8
    spring.redis.jedis.pool.max-wait=-1
    spring.redis.jedis.pool.max-idle=10
    spring.redis.jedis.pool.min-idle=2
    spring.redis.timeout=6000
```


## compile
```
 1. mvn clean install 
 2. java -jar .\target\foodtrucks-0.0.1.jar
```

## run web app
-  you should change the `host` variable in  `web/foodtrucks.html`   that is 'var host = "http://127.0.0.1:9001/"' ， change it to you own server host if you need  
-  click `web/foodtrucks.html` file to run in the google browser  , then you can search the data you want to see


## There has two Apis
```
// request to search food trucks by key word
http://127.0.0.1:9001/food/trucks?food=Chips&latlng=37.73, -122.376

// auto init datas from data origin source : https://data.sfgov.org/api/views/rqzj-sfat/rows.csv
// The data is updated every 30 minutes with the program 
com.demo.api.job.setDataToDb 

```
