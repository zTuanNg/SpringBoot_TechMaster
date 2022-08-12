package com.example.Day15_MidTerm.controller;

import com.example.Day15_MidTerm.model.Course;
import com.example.Day15_MidTerm.model.CourseDetail;
import com.example.Day15_MidTerm.service.CourseService;
import com.example.Day15_MidTerm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class CourseController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private UserService userService;


    // Get course by Type
    @GetMapping("/courses")
    public List<Course> getCourseByType(@RequestParam Optional<String> topic
                                        ,@RequestParam("keyword") Optional<String> title){
        return courseService.getAll(topic.orElse(""), title.orElse("") );
    }

    @GetMapping("/courses/{type}")
    public List<Course> filterCourse(@PathVariable String type
                                    ,@RequestParam Optional<String> topic
                                    ,@RequestParam("keyword") Optional<String> title){

//        List<Course> listCourseByType = courseService.getCourseByType(type);
//
//        if(topic.isPresent() && title.isPresent()){
//            return courseService.getCourseByTopicAndKeyword(type ,topic.get(), title.get());
//        }
//
//        if(topic.isPresent() && title.isEmpty()){
//            return courseService.getCourseByTopicAndKeyword(type, topic.get(), "");
//        }
//
//        if(title.isPresent() && topic.isEmpty()){
//            return courseService.getCourseByTopicAndKeyword(type, "", title.get());
//        }

        return courseService.getCourseByTopicAndKeyword(type, topic.orElse(""), title.orElse(""));
    }


    // Get Course Detail

    @GetMapping("/course-detail/{id}")
    public CourseDetail getCourseDetail(@PathVariable int id){
        return courseService.getCourseDetail(id);
    }
}
