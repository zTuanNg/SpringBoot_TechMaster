package com.example.Day16.repository;

import com.example.Day16.dto.UserDTO;
import com.example.Day16.model.User;
import com.example.Day16.database.FakeDB;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class UserRepository {

    public User save(User user){
        FakeDB.users.add(user);

        return user;

    }

    public List<User> getAll(){
        return FakeDB.users;
    }


    public List<User> searchUser(String name) {
        return FakeDB.users
                .stream()
                .filter(u -> u.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }



    // Delete User
    public void deleteUser(User user){
        FakeDB.users.remove(user);
    }


    // Get user by id
    public Optional<User> getUserById(int id){
        return FakeDB.users
                .stream().filter(u -> u.getId() == id)
                .findFirst();
    }


    public Optional<User> findByEmail(String email){
        return FakeDB.users.stream().filter(u -> u.getEmail().equalsIgnoreCase(email)).findFirst();
    }



}
