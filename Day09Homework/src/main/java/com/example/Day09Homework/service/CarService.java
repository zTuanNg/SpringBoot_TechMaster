package com.example.Day09Homework.service;

import com.example.Day09Homework.model.Car;
import com.example.Day09Homework.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {

    @Autowired
    private CarRepository repo;

    public List<Car> getAll(){

        return repo.findAll();
    }

    public void delete(Integer id){
       repo.deleteById(id);
    }

    public void save(Car car){
        repo.save(car);
    }

    public Car getById(Integer id){
        return repo.findById(id).get();
    }

    // sort by Model
    public List<Car> sortByModel(){
        return repo.findAll().stream().sorted(Comparator.comparing(Car::getModel)).collect(Collectors.toList());
    }

    // find by kw
    public List<Car> findCar(String keyword){
        return repo.findByKeyword(keyword);
    }


}
