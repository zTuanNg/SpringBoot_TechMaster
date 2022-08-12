package com.example.Day15_MidTerm.repository;

import com.example.Day15_MidTerm.model.Course;
import com.example.Day15_MidTerm.database.FakeDB;

import com.example.Day15_MidTerm.exception.NotFoundException;
import com.example.Day15_MidTerm.model.CourseDetail;
import com.example.Day15_MidTerm.model.Supporter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CourseRepository {



    // Lấy danh sách tất cả khóa học
    public List<Course> getAll(String topic, String title) {

        if(topic != ""){
            if(title == ""){
                return getCourseByTopic(topic);
            }else{
                return getCourseByTopicAndTitle(FakeDB.courses, topic, title);
            }
        }else{
            if(title == ""){
                return FakeDB.courses;
            }else{
                return getCourseByTitle(title);
            }
        }


    }


    // find course by id
    public Course getCourseById(int id){
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

        if(topic == ""){
            if(title != "") {
                return lstCourse.stream()
                        .filter(c -> c.getTitle().toLowerCase().contains(title.toLowerCase()))
                        .collect(Collectors.toList());
            }else{
                return lstCourse;
            }
        }else {
            if(title == ""){
                return lstCourse.stream()
                        .filter(c -> c.getTopics().contains(topic))
                        .collect(Collectors.toList());
            }else{
                return lstCourse.stream()
                        .filter(c -> c.getTitle().toLowerCase().contains(title.toLowerCase()) && c.getTopics().contains(topic))
                        .collect(Collectors.toList());
            }
        }
    }


    public Optional<Course> findCourseById(int id){
        return FakeDB.courses
                .stream()
                .filter(c -> c.getId() == id)
                .findFirst();
    }
}
