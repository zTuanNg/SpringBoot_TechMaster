package com.example.CourseManagement.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Course {
    private int id;
    private String title;
    private String description;
    private String type;
    private String image;
    private double rating;
    private List<String> topics;
    private int supporterId;




    public String getTopicString(){
        return String.join((CharSequence) " ", this.topics);
    }

    public String getPathImage(){
        if(image == null){
            return "/uploads/default.jpeg";
        }
        return "/uploads/" + this.id + "/" + this.image;
    }


}
