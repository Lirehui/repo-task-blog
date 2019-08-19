package com.reta;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;

@SpringBootApplication
@Controller
@MapperScan("com.reta")
public class DemoBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoBlogApplication.class, args);
	}

}
