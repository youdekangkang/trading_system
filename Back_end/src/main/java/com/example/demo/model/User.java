package com.example.demo.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Table(name = "users")
@Data               // 自动生成 getter、setter、equals、hashCode 和 toString 方法
@NoArgsConstructor  // 自动生成无参数的构造函数
@AllArgsConstructor // 自动生成包含所有参数的构造函数
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

    // 预留字段
    private String reservedText;
    //将预留字段用来表示账户余额
    private BigDecimal accountBalance;

    // 在设置密码时进行加密
    public void setPassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
    }


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

}
