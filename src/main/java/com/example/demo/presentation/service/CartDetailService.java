package com.example.demo.presentation.service;

import com.example.demo.DTO.CartItemDTO;

import java.util.List;
import java.util.Map;

public interface CartDetailService {
    public void addToCart(String username,Integer bookId);
    public List<CartItemDTO> getCartDetails(String username);
    public void removeFromCartById(Integer bookId, Integer userId);
}
