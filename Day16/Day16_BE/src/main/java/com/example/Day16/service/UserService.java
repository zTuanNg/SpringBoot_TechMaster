package com.example.Day16.service;

import com.example.Day16.exception.BadRequestException;
import com.example.Day16.exception.NotFoundException;
import com.example.Day16.model.User;
import com.example.Day16.dto.UserDTO;
import com.example.Day16.repository.UserRepository;
import com.example.Day16.request.CreateUserRequest;
import com.example.Day16.request.UpdateAvatarRequest;
import com.example.Day16.request.UpdatePasswordRequest;
import com.github.javafaker.Faker;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class UserService {


    private final UserRepository userRepository;
    private final Faker faker;
    private final ModelMapper modelMapper;
    private final MailService emailService;
    private final FileService fileService;

    public UserService(UserRepository userRepository, Faker faker, ModelMapper modelMapper, MailService emailService, FileService fileService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.faker = faker;
        this.emailService = emailService;
        this.fileService = fileService;
        initData();
    }

    public void initData() {
        Random rd = new Random();
        List<String> cities = new ArrayList<>(List.of("Thành phố Hà Nội", "Thành phố Đà Nẵng", "Thành phố Hồ Chí Minh"));

        for (int i = 1; i <= 25; i++) {
            int rdIndex = rd.nextInt(cities.size());
            String rdCity = cities.get(rdIndex);

            User user = User.builder()
                    .id(i)
                    .name(faker.name().fullName())
                    .email(faker.internet().emailAddress())
                    .phone(faker.phoneNumber().phoneNumber())
                    .password("111")
                    .address(rdCity)
                    .build();

            userRepository.save(user);
        }
    }


    public List<UserDTO> getAll() {
        List<User> users = userRepository.getAll();
        return users.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }


    public List<UserDTO> searchUser(String name) {
        List<User> users = userRepository.searchUser(name);

        return users
                .stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    public void deleteUser(int id) {

        User user = userRepository.getUserById(id).orElseThrow(() -> {
            throw new NotFoundException("Can not found id = " + id);
        });

        userRepository.deleteUser(user);
    }

    public User getUser(int id) {
        return userRepository.getUserById(id).orElseThrow(() -> {
            throw new NotFoundException(("Can not found this id = " + id));
        });
    }

    public User saveUser(User user) {
        String userEmail = user.getEmail();

        if (userRepository.getAll().stream().anyMatch(u -> u.getEmail().equalsIgnoreCase(userEmail))) {
            throw new BadRequestException("Email : " + userEmail + " is existed");
        }

        int id = userRepository.getAll().get(userRepository.getAll().size() - 1).getId() + 1;
        user.setId(id);

        return userRepository.save(user);
    }

    public UserDTO createUser(CreateUserRequest request) {

        // Check duplicate email
        String requestEmail = request.getEmail();
        if(userRepository.findByEmail(requestEmail).isPresent()){
            throw new BadRequestException("Email: " + requestEmail + " is existed");
        }

        // Create user
        User newUser = new User();

        newUser = modelMapper.map(request, User.class);
        newUser.setId(setIdNewUser());

        userRepository.save(newUser);

        return modelMapper.map(newUser, UserDTO.class);
    }

    public int setIdNewUser(){
        int size = userRepository.getAll().size();
        return userRepository.getAll().get(size - 1).getId() + 1;

    }


    // Forgot Password
    public void forgotPassword(int id) {

        User user = userRepository.getUserById(id).orElseThrow(() -> {
            throw new NotFoundException("Can not found id = " + id);
        });

        // Random password
        Random rd = new Random();
        String newPassword = String.valueOf(rd.nextInt(1000));
        user.setPassword(newPassword);

        // Send Mail
        emailService.sendSimpleEmail(user.getEmail(), "Forgot Password", "New Password : " + newPassword);


    }

    // Update Password
    public void updatePassword(int id, UpdatePasswordRequest request) {

        User user = userRepository.getUserById(id).orElseThrow(() -> {
            throw new NotFoundException("Can not found id = " + id);
        });

        // check equal old vs new
        if(request.getNewPassword().equals(request.getOldPassword())){
            throw new BadRequestException("New password can not equal to old password");
        }

        // check wrong password
        if(!request.getOldPassword().equals(user.getPassword())){
            throw new BadRequestException("Password is incorrect");
        }

        // update new password
        user.setPassword(request.getNewPassword());


    }

    // Upload File
    public String uploadFile(int id, MultipartFile multipartFile) {
        User user = userRepository.getUserById(id).orElseThrow(() -> {
            throw new NotFoundException("Can not found id = " + id);
        });
        return fileService.uploadFile(id, multipartFile);
    }

    // Xem file
    public byte[] readFile(int id, String fileId) {
        if(userRepository.getUserById(id).isEmpty()) {
            throw new NotFoundException("Không tìm thấy user có id = " + id);
        }
        return fileService.readFile(id, fileId);
    }

    // Lấy danh sách file
    public List<String> getFiles(int id) {
        if(userRepository.getUserById(id).isEmpty()) {
            throw new NotFoundException("Không tìm thấy user có id = " + id);
        }
        return fileService.getFiles(id);
    }

    // Xóa file
    public void deleteFile(int id, String fileId) {
        if(userRepository.getUserById(id).isEmpty()) {
            throw new NotFoundException("Không tìm thấy user có id = " + id);
        }
        fileService.deleteFile(id, fileId);
    }

    // Cập nhật avatar
    public void updateAvatar(int id, UpdateAvatarRequest request) {
        User user = userRepository.getUserById(id).orElseThrow(() -> {
            throw new NotFoundException("Không tìm thấy user có id = " + id);
        });

        user.setAvatar(request.getAvatar());
    }

}
