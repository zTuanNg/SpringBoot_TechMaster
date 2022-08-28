package com.example.CourseManagement.service;

import com.example.CourseManagement.database.FakeDB;
import com.example.CourseManagement.exception.NotFoundException;
import com.example.CourseManagement.model.Course;
import com.example.CourseManagement.model.Supporter;
import com.example.CourseManagement.model.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class CourseService {

    @Autowired
    private FileService fileService;
    // Get all courses
    public List<Course> getAll() {
        return FakeDB.courses;
    }

    // Get all supporters
    public List<Supporter> getAllSupporter(){
        return FakeDB.supporters;
    }

    // Get all topics
    public List<Topic> getAllTopic() {
        return FakeDB.topics;
    }


    // Save edit course
    public Course saveEditCourse(Course course){
        Course existingCourse = FakeDB.courses.stream().filter(c -> c.getId() == course.getId()).findFirst().get();
        existingCourse.setTitle(course.getTitle());
        existingCourse.setTopics(course.getTopics());
        existingCourse.setDescription(course.getDescription());
        existingCourse.setSupporterId(course.getSupporterId());
        existingCourse.setType(course.getType());

        if(course.getImage() != null){
            existingCourse.setImage(course.getImage());
        }

        return existingCourse;

    }

    // Save new course
    public Course saveNewCourse(Course course){
        course.setId(getNewId());
        FakeDB.courses.add(course);

        // Create folder to save image
        String rootPath = fileService.getRootPath();
        String dirPath = rootPath + "/" + course.getId();
        fileService.createFolder(dirPath);

        return course;
    }

    // Delete course
    public void deleteCourse(int id){
        Course course = getCourse(id);
        FakeDB.courses.remove(course);
    }
    public Course getCourse(int id){
        return findById(id).orElseThrow(
                () -> new NotFoundException("Could not find id :" + id) );
    }

    public Optional<Course> findById(int id) {
        return FakeDB.courses.stream().filter(todo -> todo.getId() == id).findFirst();
    }

    public int getNewId(){
        return FakeDB.courses.get(countCourses() - 1).getId() + 1;
    }

    public int countCourses(){
        return FakeDB.courses.size();
    }



}
