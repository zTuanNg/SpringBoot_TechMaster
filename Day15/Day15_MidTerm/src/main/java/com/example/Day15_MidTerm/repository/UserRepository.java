package com.example.Day15_MidTerm.repository;

import com.example.Day15_MidTerm.database.FakeDB;
import com.example.Day15_MidTerm.exception.NotFoundException;
import com.example.Day15_MidTerm.model.Supporter;
import org.apache.catalina.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository {


    public Supporter getSupporterById(int id){
        return FakeDB.supporters.stream().filter(s -> s.getId() == id).findFirst().orElseThrow(() ->{
            throw new NotFoundException("Can not found supporter with id = " + id);
        });
    }
}
