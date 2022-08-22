package com.example.Day16.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDTO {

    private int id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String avatar;

}
