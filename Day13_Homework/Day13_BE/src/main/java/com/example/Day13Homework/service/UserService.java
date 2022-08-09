package com.example.Day13Homework.service;

import com.example.Day13Homework.dto.UserDTO;
import com.example.Day13Homework.exception.NotFoundException;
import com.example.Day13Homework.model.User;
import com.example.Day13Homework.request.LoginRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private ModelMapper modelMapper;

    List<User> data = Arrays.asList(new User(1,"admin","admin01","admin@gmail.com","https://azpet.com.vn/wp-content/uploads/2022/08/C2572-C13109-1.jpg"),
                                    new User(2,"puddle","puddle01","puddle@gmail.com","https://azpet.com.vn/wp-content/uploads/2022/08/C2568-C13107-1-1024x1024.jpg"));



    public Optional<User> findByUsername(String username){
        return data.stream().filter(u -> u.getUsername().equals(username)).findFirst();
    }


    public boolean checkPassword(User user, String password){
        return user.getPassword().equals(password);
    }

    public UserDTO checkLogin(LoginRequest request){

        if(findByUsername(request.getUsername()).isEmpty()){
            throw new NotFoundException("Username incorrect..");
        }

        User user = findByUsername(request.getUsername()).get();

        if(checkPassword(user, request.getPassword())){
            return modelMapper.map(user, UserDTO.class);
        }else{
            throw new NotFoundException("Password incorrect ..");
        }


    }


}
