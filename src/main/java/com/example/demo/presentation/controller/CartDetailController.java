package com.example.demo.presentation.controller;

import com.example.demo.DTO.CartItemDTO;
import com.example.demo.persistence.entity.User;
import com.example.demo.presentation.model.request.Cart.PostCartRequest;
import com.example.demo.presentation.service.CartDetailService;
import com.example.demo.presentation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("cart-detail")
public class CartDetailController {

    @Autowired
    private CartDetailService cartDetailService;

    @Autowired
    private UserService userService;


    @PostMapping("/add")
    public ResponseEntity<String> addToCart(@RequestBody PostCartRequest cartRequest) {
        Optional<User> user = userService.findByUserName(cartRequest.getUsername());
        if (user.isPresent()) {

            cartDetailService.addToCart(cartRequest.getUsername(), cartRequest.getBookId());
            return ResponseEntity.ok("Sách đã được thêm vào giỏ hàng.");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Không tìm thấy người dùng.");
    }

    @GetMapping("/view")
    public ResponseEntity<List<CartItemDTO>> viewCart(@RequestParam("username") String username) {
        List<CartItemDTO> cartItems = cartDetailService.getCartDetails(username);
        if (cartItems != null && !cartItems.isEmpty()) {
            return ResponseEntity.ok(cartItems);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @PostMapping("/remove")
    public ResponseEntity<String> removeFromCart(@RequestBody PostCartRequest cartRequest) {
        try {
            cartDetailService.removeFromCartById(cartRequest.getBookId(),cartRequest.getUserId());
            return ResponseEntity.ok("Cart detail with ID " + cartRequest.getId() + " has been removed successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
