package com.example.demo.Repository;

import com.example.demo.entity.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "hinh-thuc-thanh-toan")
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Integer> {
}
