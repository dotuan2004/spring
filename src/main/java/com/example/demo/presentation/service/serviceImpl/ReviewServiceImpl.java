package com.example.demo.presentation.service.serviceImpl;

import com.example.demo.DTO.ReviewDTO;
import com.example.demo.persistence.Repository.BookRepository;
import com.example.demo.persistence.Repository.ReviewRepository;
import com.example.demo.persistence.Repository.UserRepository;
import com.example.demo.persistence.entity.Book;
import com.example.demo.persistence.entity.Review;
import com.example.demo.persistence.entity.User;
import com.example.demo.presentation.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Override
    @Transactional
    public Review saveDanhGia(ReviewDTO DTO, Integer bookId) {
        Book book = bookRepository.findByBookId(bookId);
        if (book == null) {
            throw new RuntimeException("khong tim thay sach");
        }

        User user = userRepository.findByUsername(DTO.getUsername());
        if (user == null) {
            throw new RuntimeException("khong tim thay user");
        }

        Review review = new Review();
        review.setBook(book);  // Book should be managed by the persistence context
        review.setUser(user);
        review.setRating(DTO.getRating());
        review.setContent(DTO.getContent());

        return reviewRepository.save(review);
    }


}
