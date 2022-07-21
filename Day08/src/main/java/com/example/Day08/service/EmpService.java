package com.example.Day08.service;

import com.example.Day08.model.Employee;

import java.util.List;

public interface EmpService {

    public void readData(String csv);

    public List<Employee> getAll();

    public Employee findEmpById(int id);
    public void deleteEmpById(int id);

}


