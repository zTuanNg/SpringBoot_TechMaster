package com.example.Day08.controller;

import com.example.Day08.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.Day08.service.EmpService;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmpController {

    @Autowired
    private EmpService service;

    @GetMapping
    public String getAll(Model model){
        List<Employee> lst = service.getAll();
        model.addAttribute("ems",lst);
        return "index";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmp(@PathVariable int id){
        service.deleteEmpById(id);
        return "redirect:/index";
    }

//    @GetMapping("/delete/{id}")
//    public List<Employee> delete(@PathVariable int id){
//        service.deleteEmpById(id);
//        return service.getAll();
//    }

}
