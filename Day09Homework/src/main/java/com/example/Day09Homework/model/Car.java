package com.example.Day09Homework.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Car")
@Getter
@Setter
@ToString
@RequiredArgsConstructor

public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String model;
    private int year;
    private String brand;
    private String image;

    {
        this.image = this.randomImagePath();
    }






    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Car car = (Car) o;
        return id != null && Objects.equals(id, car.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Transient
    public String randomImagePath(){
        int rdNUmber = (int) (Math.floor(Math.random()*4)+1);
//        return "/Cars/"+rdNUmber+".jpeg";
        return "/car-photos/" + this.id +"/" + this.image;


    }
}
