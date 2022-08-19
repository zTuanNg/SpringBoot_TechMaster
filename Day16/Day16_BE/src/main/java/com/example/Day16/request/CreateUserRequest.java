package com.example.Day16.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {

    private String name;
    private String email;
    private String phone;
    private String address;
    private String avatar;
    private String password;

}
