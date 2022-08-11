package com.example.coursesample.repository;

import com.example.coursesample.database.FakeDB;
import com.example.coursesample.model.Course;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseRepository {
    // Repository : Chứa các phương thức để thao tác với database

    // Ví dụ demo
    // Lấy danh sách tất cả khóa học
    public List<Course> getAll() {
        return FakeDB.courses;
    }
}
