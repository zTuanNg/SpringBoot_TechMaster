package com.example.Day07_Homework.service;

import com.example.Day07_Homework.model.Person;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface PersonService {
    public void getData(String csv);
    public List<Person> getAll();
    public List<Person> sortPersonByName();
    public List<Person> sortPersonByJob();
    public List<Person> sortPersonByCity();
    public Map<Integer,Set<String>> findTop5Job();
    public Map<Integer,Set<String>> findTop5City();

    public Map<String,Set<String>> findTopJobCity();

    public Map<String,Set<String>> findTop5JobByCity();
}
