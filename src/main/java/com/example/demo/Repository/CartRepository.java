package com.example.demo.Repository;

import com.example.demo.entity.Cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "gio-hang")
public interface CartRepository extends JpaRepository<Cart, Integer> {
}
