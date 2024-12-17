package com.example.demo.service;

import com.example.demo.Repository.UserRepository;
import com.example.demo.entity.ErrorNotification;
import com.example.demo.entity.User;
import com.example.demo.entity.ErrorNotification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class TaiKhoanService {
    @Autowired
    UserRepository nguoiDungRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public ResponseEntity<?> dangKyNguoiDung(User nguoiDung){
        if(nguoiDungRepository.existsByUsername(nguoiDung.getUsername())){
            return ResponseEntity.badRequest().body(new ErrorNotification("Tên Đăng Nhập Đã Tồn Tại"));
        }
        if(nguoiDungRepository.existsByEmail(nguoiDung.getEmail())){
            return ResponseEntity.badRequest().body(new ErrorNotification("Email Đã Tồn Tại"));
        }
        String encode=bCryptPasswordEncoder.encode(nguoiDung.getPassword());
        nguoiDung.setPassword(encode);
        nguoiDungRepository.save(nguoiDung);
        return ResponseEntity.ok("đã đăng Ký thành công");
    }
}
