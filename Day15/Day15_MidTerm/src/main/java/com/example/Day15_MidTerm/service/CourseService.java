package com.example.Day15_MidTerm.service;

import com.example.Day15_MidTerm.model.Course;
import com.example.Day15_MidTerm.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;


    // Get all
    public List<Course> getAll() {
        return courseRepository.getAll();
    }

    // Get course by Id
    public Course getCourseById(int id){
        return courseRepository.getCourse(id);
    }

    // Get course by type
    public List<Course> getCourseByType(String type){
        return courseRepository.getCourseByType(type);
    }

    // Get course by title
    public List<Course> getCourseByTitle(String title){
        return courseRepository.getCourseByTitle(title);
    }

    // Get course by topic
    public List<Course> getCourseByTopic(String topic){
        return courseRepository.getCourseByTopic(topic);
    }

    // Get course by topic and title
    public List<Course> getCourseByTopicAndKeyword(String type,String topic, String keyword){
        return courseRepository.getCourseByTopicAndTitle(getCourseByType(type), topic, keyword);
    }




}
