package com.example.demo.persistence.Repository;

import com.example.demo.persistence.entity.CartDetail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(path = "chi-tiet-gio-hang")
public interface CartDetailRepository extends JpaRepository<CartDetail, Integer> {
    Optional<CartDetail> findByBook_BookIdAndUser_UserId(Integer bookId, Integer userId);
}
