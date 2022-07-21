package com.example.Day07_Homework.controller;

import com.example.Day07_Homework.model.Person;
import com.example.Day07_Homework.service.PersonService;
import com.example.Day07_Homework.service.PersonServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService service;

    @Autowired
    private PersonServiceImp s1;

    @GetMapping
    public List<Person> getData(){
        return service.getAll();
    }

    @GetMapping("/sort_by_name")
    public List<Person> sortByName(){
        return service.sortPersonByName();
    }

    @GetMapping("/top5_job")
    public Map<Integer,Set<String>> findTop5Job(){
        return service.findTop5Job();
    }

    @GetMapping("/top5_city")
    public Map<Integer,Set<String>> findTop5City(){
        return service.findTop5City();
    }

    @GetMapping("/top_job_city")
    public Map<String,Set<String>> findTopJobCity(){
        return service.findTopJobCity();
    }

    @GetMapping("/top5_job_city")
    public Map<String,Set<String>> findTop5JobInCity(){
        return service.findTop5JobByCity();
    }

}
