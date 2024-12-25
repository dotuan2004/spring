package com.example.demo.presentation.service;

import com.example.demo.DTO.ReviewDTO;
import com.example.demo.persistence.entity.Review;


public interface ReviewService {
    public Review saveDanhGia(ReviewDTO DTO, Integer bookID);
}
