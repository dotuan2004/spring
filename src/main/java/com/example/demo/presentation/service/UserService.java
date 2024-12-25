package com.example.demo.presentation.service;

import com.example.demo.persistence.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    public User findByUserName(String tenDangNhap);
}
