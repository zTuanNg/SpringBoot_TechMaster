package com.example.Day16;

import com.github.javafaker.Faker;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Day16Application {

	public static void main(String[] args) {
		SpringApplication.run(Day16Application.class, args);
	}


	@Bean
	public Faker faker(){
		return new Faker();
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

}
