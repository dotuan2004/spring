package com.example.demo.persistence.Repository;

import com.example.demo.persistence.entity.CartDetail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "chi-tiet-gio-hang")
public interface CartDetailRepository extends JpaRepository<CartDetail, Integer> {
    CartDetail findByCartDetailId(Integer cartDetailId);
}
