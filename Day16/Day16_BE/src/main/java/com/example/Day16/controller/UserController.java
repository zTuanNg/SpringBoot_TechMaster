package com.example.Day16.controller;

import com.example.Day16.repository.UserRepository;
import com.example.Day16.request.CreateUserRequest;
import com.example.Day16.request.UpdateAvatarRequest;
import com.example.Day16.request.UpdatePasswordRequest;
import com.example.Day16.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<?> getUsers(){
        return ResponseEntity.ok(userService.getAll());
    }

    // Find by name
    @GetMapping("/users/search")
    public ResponseEntity<?> searchUser(@RequestParam String name){

        return ResponseEntity.ok(userService.searchUser(name));

    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/users/detail/{id}")
    public ResponseEntity<?> getUser( @PathVariable int id){

        return ResponseEntity.ok(userService.getUser(id));
    }

    @PostMapping("/users")
    // Create User
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest request){

        return ResponseEntity.ok(userService.createUser(request));

    }


    // Forgot password
    @GetMapping("/users/{id}/forgot-password")
    public ResponseEntity<?> forgotPassword( @PathVariable int id){

        userService.forgotPassword(id);
        return ResponseEntity.noContent().build();
    }

    // Update Password
    @PutMapping("/users/{id}/update-password")
    public ResponseEntity<?> updatePassword(@PathVariable int id, @Valid @RequestBody UpdatePasswordRequest request){

        userService.updatePassword(id, request);

        return ResponseEntity.noContent().build();
    }


    // --------------FILE-----------------

    // Upload file
    @PostMapping("/users/{id}/files")
    public ResponseEntity<?> uploadFile(@ModelAttribute("file") MultipartFile file, @PathVariable int id) {
        String filePath =  userService.uploadFile(id, file);
        return ResponseEntity.ok(filePath);
    }

    // Lấy ds file
    @GetMapping("/users/{id}/files")
    public ResponseEntity<?> getFiles(@PathVariable int id) {
        List<String> files = userService.getFiles(id);
        return ResponseEntity.ok(files);
    }

    // Xóa file
    @DeleteMapping("/users/{id}/files/{fileId}")
    public ResponseEntity<?> deleteFile(@PathVariable int id, @PathVariable String fileId) {
        userService.deleteFile(id, fileId);
        return ResponseEntity.noContent().build();
    }

    // Thay đổi avatar
    @PutMapping("/users/{id}/update-avatar")
    public ResponseEntity<?> updateAvatar(@PathVariable int id, @RequestBody UpdateAvatarRequest request) {
        userService.updateAvatar(id, request);
        return ResponseEntity.noContent().build();
    }



}
