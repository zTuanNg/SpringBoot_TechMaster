package com.example.CourseManagement;

import com.example.CourseManagement.database.FakeDB;
import com.example.CourseManagement.model.Course;
import com.example.CourseManagement.service.CourseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CourseManagementApplicationTests {

	@Autowired
	private CourseService service;

	@Test
	public void testGetAll() {

		Course course = new Course();
		Course course1 = new Course();
		service.saveNewCourse(course);
		service.saveNewCourse(course1);
		List<Course> lst = service.getAll();
		lst.stream().forEach(System.out::println);
	}

}
