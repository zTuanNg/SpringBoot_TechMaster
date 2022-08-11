package com.example.coursesample.controller;

import com.example.coursesample.model.Course;
import com.example.coursesample.service.CourseService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CourseController {
    @Autowired
    private CourseService courseService;
    // Ví dụ demo
    // Lấy danh sách tất cả khóa học
    @GetMapping("/courses")
    public List<Course> getAllCourse() {
        return courseService.getAll();
    }
}
