package com.example.CourseManagement.controller;

import com.example.CourseManagement.database.FakeDB;
import com.example.CourseManagement.model.Course;
import com.example.CourseManagement.model.Supporter;
import com.example.CourseManagement.model.Topic;
import com.example.CourseManagement.service.CourseService;
import com.example.CourseManagement.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private FileService fileService;

    @GetMapping({"/courses", "/"})
    public String getAllCourses(Model model){
        List<Course> course = courseService.getAll();
        int totalCourses = courseService.countCourses();
        model.addAttribute("courses", course);
        model.addAttribute("totalCourse", totalCourses);
        return "course-list";

    }

    @GetMapping("/courses/detail/{id}")
    public String courseDetail(@PathVariable int id,
                               Model model){
        Course course = courseService.getCourse(id);

        List<Supporter> supporters = courseService.getAllSupporter();
        List<Topic> lstTopic = courseService.getAllTopic();

        model.addAttribute("course",course);
        model.addAttribute("supporters",supporters);
        model.addAttribute("lstTopic", lstTopic);

        return "course-edit";
    }


    @PostMapping("/courses/save")
    public String saveCourse(@ModelAttribute("course") Course course
                            , @RequestParam("picture") MultipartFile multipartFile) throws IOException {

        System.out.println("image: "+ multipartFile.isEmpty());
        System.out.println(course);

        if (!multipartFile.isEmpty()) {


            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            String uploadDir = fileService.getRootPath() + "/" + course.getId();
            fileService.cleanDir(uploadDir);
            fileService.saveFile(uploadDir, fileName, multipartFile );

            course.setImage(fileName);
        }


       Course existCourse =  courseService.saveEditCourse(course);
       System.out.println("exist course: " + existCourse);

        return "redirect:/courses";
    }


    @GetMapping("/courses/new")
    public String newCourse(Model model){

        Course course = new Course();

        List<Supporter> supporters = courseService.getAllSupporter();
        List<Topic> lstTopic = courseService.getAllTopic();

        model.addAttribute("course", course);
        model.addAttribute("supporters",supporters);
        model.addAttribute("lstTopic", lstTopic);

        return "course-create";
    }

    @PostMapping("/courses/new")
    public String createCourse(@ModelAttribute("course") Course course){

        courseService.saveNewCourse(course);

        return "redirect:/courses";
    }

    @GetMapping("/courses/delete/{id}")
    public String deleteCourse(@PathVariable int id){
        courseService.deleteCourse(id);

        return "redirect:/courses";
    }

}
