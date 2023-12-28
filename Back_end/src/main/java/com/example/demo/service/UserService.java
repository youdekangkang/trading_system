package com.example.demo.service;

import com.example.demo.ApiResponse;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    // 注册新用户
    public User registerNewUser(User newUser) {
//        String encodedPassword = new BCryptPasswordEncoder().encode(newUser.getPassword());
//        newUser.setPassword(encodedPassword);
        return userRepository.save(newUser);
    }

    // 用户登录
    public ApiResponse loginUser(String username, String password) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (!user.getActive()) {
                return new ApiResponse(false, "Account is disabled.");
            }
            if (new BCryptPasswordEncoder().matches(password, user.getPassword())) {
                return new ApiResponse(true, "Login successful.");
            }
        }
        return new ApiResponse(false, "Login failed.");
    }


    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }


    // 信息修改
    public ApiResponse updateUser(String username, User userDetails) {
        return userRepository.findByUsername(username)
                .map(user -> {
                    user.setPassword(new BCryptPasswordEncoder().encode(userDetails.getPassword()));
                    user.setEmail(userDetails.getEmail());
                    userRepository.save(user);
                    return new ApiResponse(true, "User updated successfully.");
                }).orElse(new ApiResponse(false, "User not found."));
    }


    // 用户禁用
    public ApiResponse disableUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(user -> {
                    user.setActive(false); // 设置用户为非激活状态，即禁用
                    userRepository.save(user);
                    return new ApiResponse(true, "User disabled successfully.");
                }).orElse(new ApiResponse(false, "User not found."));
    }
    
    // 查找用户
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

}