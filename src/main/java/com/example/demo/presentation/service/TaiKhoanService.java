package com.example.demo.presentation.service;
import com.example.demo.persistence.Repository.UserRepository;
import com.example.demo.persistence.entity.ErrorNotification;
import com.example.demo.persistence.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TaiKhoanService {
    @Autowired
    UserRepository nguoiDungRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private EmailService emailService;

    public ResponseEntity<?> dangKyNguoiDung(User nguoiDung){
        if(nguoiDungRepository.existsByUsername(nguoiDung.getUsername())){
            return ResponseEntity.badRequest().body(new ErrorNotification("Tên Đăng Nhập Đã Tồn Tại"));
        }

        String encode=bCryptPasswordEncoder.encode(nguoiDung.getPassword());
        nguoiDung.setPassword(encode);
        nguoiDung.setActiveCode(UUID.randomUUID().toString());
        nguoiDung.setActive(false);

        nguoiDungRepository.save(nguoiDung);
        sendEMailActive(nguoiDung.getEmail(),nguoiDung.getActiveCode());
        return ResponseEntity.ok("đã đăng Ký thành công");
    }

    public void sendEMailActive(String email,String activeCode){
        String subject="kích hoạt tài khoản của bạn tại web";
        String text="Vui lòng sử dụng mã sau để kich hoat <"+email+">:<br/> <h1>"+activeCode+"</h1>";
        emailService.sendEmail("dodinhtuanyb2k4@gmail.com",email,text,subject);
    }
}
