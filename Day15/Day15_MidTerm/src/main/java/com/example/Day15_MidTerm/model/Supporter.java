package com.example.Day15_MidTerm.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Supporter {
    private int id;
    private String name;
    private String email;
    private String phone;
    private String avatar;
}
