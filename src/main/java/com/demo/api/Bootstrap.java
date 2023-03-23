package com.demo.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class Bootstrap {
	// 获取日志对象
	//	private static final Logger logger = LoggerFactory.getLogger(Bootstrap.class);
	public static void main(String[] args) {
		SpringApplication.run(Bootstrap.class, args);
	}

}
