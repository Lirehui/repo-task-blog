package com.reta;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
@MapperScan("com.reta")
public class DemoBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoBlogApplication.class, args);
	}
	
	@GetMapping("hello")
	public String hello() {
		return "hello";
	}
	

}
