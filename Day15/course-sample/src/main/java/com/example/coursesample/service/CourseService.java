package com.example.coursesample.service;

import com.example.coursesample.model.Course;
import com.example.coursesample.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    // Ví dụ demo
    // Lấy danh sách tất cả khóa học
    public List<Course> getAll() {
        return courseRepository.getAll();
    }
}
