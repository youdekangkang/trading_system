package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String username;

    @Column(nullable = false)
    private String password;
    private String email;
    private LocalDateTime updatedAt;

    // 用户状态
    private Boolean active = true;


    public User() {
    }

    public User(String username, String password, String email) {
        this.username = username;
        setPassword(password); // 使用加密方法设置密码
        this.email = email;
        this.updatedAt = LocalDateTime.now();
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    // 在设置密码时进行加密
    public void setPassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setActive(boolean b) { this.active = b;
    }

    public boolean getActive() {return active;}
}
