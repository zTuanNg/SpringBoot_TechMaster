package com.example.Day09Homework.controller;

import com.example.Day09Homework.model.Car;
import com.example.Day09Homework.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CarController {

    @Autowired
    private CarService service;

    @GetMapping
    public String getAll(Model model, String keyword){

        if(keyword == null){
            List<Car> cars = service.getAll();
            model.addAttribute("cars",cars);

        }else{
            List<Car> cars = service.findCar(keyword);
            model.addAttribute("cars",cars);
        }

        return "index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id){
        service.delete(id);
        return "redirect:/";

    }

    @GetMapping("/new")
    public String create(Model model){

        Car car = new Car();
        model.addAttribute("car",car);

        return "create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("car") Car car){

        service.save(car);
        return "redirect:/";

    }

    @GetMapping("edit/{id}")
    public String editCar(@PathVariable("id") Integer id,
                          Model model,
                          RedirectAttributes redirectAttributes){

        Car car = service.getById(id);
        model.addAttribute("car",car);

        return "create";

    }

    @GetMapping("/sort")
    public String sort(Model model){

        List<Car> cars = service.sortByModel();
        model.addAttribute("cars",cars);
        return "index";
    }
}
