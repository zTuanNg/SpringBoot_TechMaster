package com.example.Day09Homework;

import com.example.Day09Homework.model.Car;
import com.example.Day09Homework.service.CarService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@SpringBootTest
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = true)
class AppTests {

	@Autowired
	private CarService service;

	@Test
	void contextLoads() {
	}

	@Test
	public void getAll(){
		List<Car> data = service.getAll();
		System.out.println(data.size());
	}

}
