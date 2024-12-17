package com.example.demo.Repository;


import com.example.demo.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "chi-tiet-don-hang")
public interface OrderDetailRepository extends JpaRepository<OrderDetail,Integer> {
}
