package com.example.Day09Homework.repository;

import com.example.Day09Homework.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarRepository extends JpaRepository<Car,Integer> {

    @Query(value = "SELECT * FROM Car c WHERE c.model like %:keyword% OR c.brand like %:keyword%",nativeQuery = true)
    List<Car> findByKeyword(@Param("keyword") String keyword);
}
