package com.example.demo.controller;


import com.example.demo.entity.User;
import com.example.demo.security.JwtResponse;
import com.example.demo.security.loginRequest;
import com.example.demo.service.JwtSevice;
import com.example.demo.service.TaiKhoanService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/tai-khoan")
public class TaiKhoanController {
    @Autowired
    private TaiKhoanService taiKhoanService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtSevice jwtSevice;

    @PostMapping("/dang-ky")
    public ResponseEntity<?> dangKyNguoiDung(@Validated @RequestBody User nguoiDung){
        ResponseEntity<?> response=taiKhoanService.dangKyNguoiDung(nguoiDung);
        return response;
    }
    @PostMapping("/dang-nhap")
    public ResponseEntity<?> dangNhap(@RequestBody loginRequest loginRequest){
        try {
            Authentication authentication=authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword())
            );
            if(authentication.isAuthenticated()){
                final String jwt=jwtSevice.generateToken(loginRequest.getUsername());
                return ResponseEntity.ok(new JwtResponse(jwt));
            }
        }catch (AuthenticationException a){
            return ResponseEntity.badRequest().body("tên đăng nhập hoặc mật khẩu không chính xác");
        }
        return ResponseEntity.badRequest().body("xác thực không thành công");
    }
}
