package com.example.demo.DTO;

import com.example.demo.entity.Book;
import com.example.demo.entity.User;
import lombok.Data;

@Data
public class ReviewDTO {
    private Integer rating;
    private String content;
    private String username;
    private Integer bookID;
}
