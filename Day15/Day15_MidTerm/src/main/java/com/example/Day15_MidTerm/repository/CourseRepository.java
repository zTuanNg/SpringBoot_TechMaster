package com.example.Day15_MidTerm.repository;

import com.example.Day15_MidTerm.model.Course;
import com.example.Day15_MidTerm.database.FakeDB;

import com.example.Day15_MidTerm.exception.NotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CourseRepository {
    // Repository : Chứa các phương thức để thao tác với database

    // Ví dụ demo
    // Lấy danh sách tất cả khóa học
    public List<Course> getAll() {
        return FakeDB.courses;
    }


    // find course by id
    public Course getCourse(int id){
        return findCourseById(id).orElseThrow(() ->{
            throw new NotFoundException("Course with id = " + id + " can not found");
        });
    }


    // Get course by type
    public List<Course> getCourseByType(String type){
        return FakeDB.courses.stream()
                .filter(c -> c.getType().equalsIgnoreCase(type))
                .collect(Collectors.toList());

    }

    // Get course by title
    public List<Course> getCourseByTitle(String title){
        return FakeDB.courses.stream()
                .filter(c -> c.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    // Get course by topic
    public List<Course> getCourseByTopic(String topic){
      return FakeDB.courses.stream().filter(c -> c.getTopics().contains(topic)).collect(Collectors.toList());
    }

    // Get course by topic and title
    public List<Course> getCourseByTopicAndTitle(List<Course>lstCourse, String topic, String title){

        if(topic == null){
            if(title != null) {
                return lstCourse.stream()
                        .filter(c -> c.getTitle().toLowerCase().contains(title.toLowerCase()))
                        .collect(Collectors.toList());
            }else{
                return lstCourse;
            }
        }

        if(title == null){

            return lstCourse.stream()
                            .filter(c -> c.getTopics().contains(topic))
                            .collect(Collectors.toList());
        }

        else {
            return lstCourse.stream()
                             .filter(c -> c.getTitle().toLowerCase().contains(title.toLowerCase()) && c.getTopics().contains(topic))
                             .collect(Collectors.toList());
        }

    }


    public Optional<Course> findCourseById(int id){
        return FakeDB.courses
                .stream()
                .filter(c -> c.getId() == id)
                .findFirst();
    }
}
