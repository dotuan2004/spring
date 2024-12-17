package com.example.demo.service;

import com.example.demo.Repository.UserRepository;
import com.example.demo.Repository.RoleRepository;
import com.example.demo.entity.User;
import com.example.demo.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Service
public class UserServiceImpl implements UserService{


    UserRepository nguoiDungRepository;

    RoleRepository quyenRepository;

    @Autowired
    public UserServiceImpl(UserRepository nguoiDungRepository, RoleRepository quyenRepository) {
        this.nguoiDungRepository = nguoiDungRepository;
        this.quyenRepository = quyenRepository;
    }

    @Override
    public User findByUserName(String tenDangNhap) {
        return nguoiDungRepository.findByUsername(tenDangNhap);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User nguoiDung=nguoiDungRepository.findByUsername(username);
        if (nguoiDung == null) {
            throw new UsernameNotFoundException("Không tìm thấy người dùng với tên đăng nhập: " + username);
        }

        return new org.springframework.security.core.userdetails.User(nguoiDung.getUsername(), nguoiDung.getPassword(),rolesToAuthorities(nguoiDung.getRoleList()));
    }

    private Collection<? extends GrantedAuthority> rolesToAuthorities(Collection<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return authorities;
    }
}

