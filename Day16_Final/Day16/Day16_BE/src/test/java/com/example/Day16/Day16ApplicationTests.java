package com.example.Day16;

import com.example.Day16.dto.UserDTO;
import com.example.Day16.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class Day16ApplicationTests {

	@Autowired
	private UserService userService;


	@Test
	public void getAllDto(){

		List<UserDTO> usersDTO = userService.getAll();

		usersDTO.forEach(System.out::println);

		Assertions.assertThat(usersDTO.size()).isEqualTo(25);


	}

}
