package com.example.CourseManagement.database;

import com.example.CourseManagement.model.Course;
import com.example.CourseManagement.model.Supporter;
import com.example.CourseManagement.model.Topic;

import java.util.ArrayList;
import java.util.List;

public class FakeDB {
    public static List<Course> courses = new ArrayList<>(List.of(
            new Course(1, "Spring Boot - Web Back End", "Spring Boot là một module của Spring Framework giúp giảm tải các cấu hình cho phép xây dựng nhanh chóng một ứng dụng độc lập. Spring Boot cung cấp sẵn các Embedded HTTP servers (Tomcat, Jetty, …), các plugins để phát triển và test một cách dễ dàng.", "onlab", "1.jpeg", 4.5, new ArrayList<>(List.of("Backend")), 3),
            new Course(2, "Lập trình iOS Swift căn bản cập nhật 2022", "Swift là một trong những ngôn ngữ đang phát triển mạnh mẽ nhất hiện nay", "onlab", "2.jpeg", 4.7, new ArrayList<>(List.of("Mobile")), 1),
            new Course(3, "Khoá học Lập trình Arduino Scratch cho trẻ em", "Phù hợp với học sinh chưa bao giờ code. 3 buổi đầu sử dụng ngôn ngữ Scratch kéo thả trực quan để làm quen với lập trình.", "onlab", "3.png", 4.3, new ArrayList<>(List.of("Database")), 2),
            new Course(4, "Spring Boot - Web Back End", "Spring Boot là một module của Spring Framework giúp giảm tải các cấu hình cho phép xây dựng nhanh chóng một ứng dụng độc lập. Spring Boot cung cấp sẵn các Embedded HTTP servers (Tomcat, Jetty, …), các plugins để phát triển và test một cách dễ dàng.", "onlab", "4.jpeg", 4.5, new ArrayList<>(List.of("Backend")), 3),
            new Course(5, "Lập trình iOS Swift căn bản cập nhật 2022", "Swift là một trong những ngôn ngữ đang phát triển mạnh mẽ nhất hiện nay", "onlab", "5.jpeg", 4.7, new ArrayList<>(List.of("Backend")), 1),
            new Course(6, "Khoá học Lập trình Arduino Scratch cho trẻ em", "Phù hợp với học sinh chưa bao giờ code. 3 buổi đầu sử dụng ngôn ngữ Scratch kéo thả trực quan để làm quen với lập trình.", "onlab", "6.png", 4.3, new ArrayList<>(List.of("Database")), 2),
            new Course(7, "Spring Boot - Web Back End", "Spring Boot là một module của Spring Framework giúp giảm tải các cấu hình cho phép xây dựng nhanh chóng một ứng dụng độc lập. Spring Boot cung cấp sẵn các Embedded HTTP servers (Tomcat, Jetty, …), các plugins để phát triển và test một cách dễ dàng.", "online", "7.jpeg", 4.5, new ArrayList<>(List.of("Backend")), 3),
            new Course(8, "Lập trình iOS Swift căn bản cập nhật 2022", "Swift là một trong những ngôn ngữ đang phát triển mạnh mẽ nhất hiện nay", "online", "8.jpeg", 4.7, new ArrayList<>(List.of("Backend")), 1),
            new Course(9, "Khoá học Lập trình Arduino Scratch cho trẻ em", "Phù hợp với học sinh chưa bao giờ code. 3 buổi đầu sử dụng ngôn ngữ Scratch kéo thả trực quan để làm quen với lập trình.", "online", "9.png", 4.3, new ArrayList<>(List.of("Database")), 2),
            new Course(10, "Spring Boot - Web Back End", "Spring Boot là một module của Spring Framework giúp giảm tải các cấu hình cho phép xây dựng nhanh chóng một ứng dụng độc lập. Spring Boot cung cấp sẵn các Embedded HTTP servers (Tomcat, Jetty, …), các plugins để phát triển và test một cách dễ dàng.", "online", "10.jpeg", 4.5, new ArrayList<>(List.of("Backend")), 3)
    ));

    public static List<Supporter> supporters = new ArrayList<>(List.of(
            new Supporter(1, "Phạm Thị Mẫn"),
            new Supporter(2, "Nguyễn Đức Thịnh"),
            new Supporter(3, "Nguyễn Thanh Hương")

    ));

    public static List<Topic> topics = new ArrayList<>(List.of(
            new Topic(1, "Backend"),
            new Topic(2, "Frontend"),
            new Topic(3, "Mobile"),
            new Topic(4, "Web"),
            new Topic(5, "Database"),
            new Topic(6, "Devops")
    ));
}
