package com.example.demo.service;

import com.example.demo.DTO.BookDTO;
import com.example.demo.DTO.ReviewDTO;
import com.example.demo.entity.Review;

public interface ReviewService {
    public Review saveDanhGia(ReviewDTO DTO,Integer bookID);
}
