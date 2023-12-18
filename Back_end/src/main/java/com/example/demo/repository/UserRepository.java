package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // 添加自定义的方法
    User findByUsernameAndPassword(String username, String password);
//    User findByUsername(String username);
    Optional<User> findByUsername(String username);
}