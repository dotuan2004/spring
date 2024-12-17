package com.example.demo.Repository;

import com.example.demo.entity.CartDetail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "chi-tiet-gio-hang")
public interface CartDetailRepository extends JpaRepository<CartDetail, Integer> {
}
