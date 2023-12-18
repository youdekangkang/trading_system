package com.example.demo.controller;

import com.example.demo.ApiResponse;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User newUser) {
        User user = userService.registerNewUser(newUser);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> loginUser(@RequestBody Map<String, String> credentials) {
        ApiResponse response = userService.loginUser(credentials.get("username"), credentials.get("password"));
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.UNAUTHORIZED);
    }


    @GetMapping("/getByName/{username}")
    public ResponseEntity<ApiResponse> getUserByUsername(@PathVariable String username) {
        User user = userService.getUserByUsername(username);
        if (user != null) {
            String res = "Username: "+user.getUsername()+", Email: "+user.getEmail();
            return ResponseEntity.ok(new ApiResponse(true, res));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(false, "User not found."));
        }
    }


    @PutMapping("/update/{username}")
    public ResponseEntity<ApiResponse> updateUser(@PathVariable String username, @RequestBody User userDetails) {
        ApiResponse response = userService.updateUser(username, userDetails);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{username}")
    public ResponseEntity<ApiResponse> disableUser(@PathVariable String username) {
        ApiResponse response = userService.disableUserByUsername(username);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

}