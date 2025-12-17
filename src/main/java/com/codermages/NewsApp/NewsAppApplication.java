package com.codermages.NewsApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication//exclude = {DataSourceAutoConfiguration.class })
public class NewsAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsAppApplication.class, args);
	}

}
