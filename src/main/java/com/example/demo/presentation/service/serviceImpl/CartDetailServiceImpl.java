package com.example.demo.presentation.service.serviceImpl;



import com.example.demo.DTO.CartItemDTO;
import com.example.demo.exception.ExceptionConstant;
import com.example.demo.exception.HrmException;
import com.example.demo.persistence.Repository.BookRepository;
import com.example.demo.persistence.Repository.CartDetailRepository;
import com.example.demo.persistence.Repository.ImageRepository;
import com.example.demo.persistence.Repository.UserRepository;
import com.example.demo.persistence.entity.Book;
import com.example.demo.persistence.entity.CartDetail;
import com.example.demo.persistence.entity.Image;
import com.example.demo.persistence.entity.User;
import com.example.demo.presentation.service.CartDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Constants;
import org.springframework.stereotype.Service;
import java.util.*;
@Slf4j
@Service
@RequiredArgsConstructor
public class CartDetailServiceImpl implements CartDetailService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartDetailRepository cartDetailRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private BookRepository bookRepository;
    @Override
    public void addToCart(String username,Integer bookId) {
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isEmpty()){
            throw new HrmException(ExceptionConstant.INPUT_EMPTY);
        }
        Optional<Book> book=bookRepository.findByBookId(bookId);
        if(book.isEmpty()){
            throw new HrmException(ExceptionConstant.INPUT_EMPTY);
        }

        if (user.isPresent()) {
            CartDetail cartDetail = new CartDetail();
            cartDetail.setBook(book.get());
            cartDetail.setQuantity(1);
            cartDetail.setPrice(book.get().getListPrice());
            cartDetail.setUser(user.get());
            cartDetailRepository.save(cartDetail);
        }
    }
    public List<CartItemDTO> getCartDetails(String username) {

        Optional<User> user = userRepository.findByUsername(username);

        if (user != null) {

            List<CartItemDTO> cartItems = new ArrayList<>();

            for (CartDetail cartDetail : user.get().getCartDetails()) {
                CartItemDTO item = new CartItemDTO();
                item.setId(cartDetail.getCartDetailId());
                item.setTenSach(cartDetail.getBook().getBookName());
                item.setPrice(cartDetail.getBook().getListPrice());
                item.setQuantity(cartDetail.getQuantity());

                List<Image> images = cartDetail.getBook().getImages();
                if (images != null && !images.isEmpty()) {
                    item.setImageUrl(images.get(0).getImageData());
                } else {
                    item.setImageUrl(null);
                }


                cartItems.add(item);
            }

            return cartItems;
        }
        return null;
    }
    public void removeFromCartById(Integer bookId, Integer userId) {
        log.info("Removing book with ID {} for user ID {}", bookId, userId);

        // Tìm mục giỏ hàng theo sách và người dùng
        Optional<CartDetail> cartDetailOptional = cartDetailRepository.findByBook_BookIdAndUser_UserId(bookId, userId);

        // Kiểm tra nếu tồn tại
        if (cartDetailOptional.isPresent()) {
            // Xóa mục khỏi giỏ hàng
            cartDetailRepository.delete(cartDetailOptional.get());
            log.info("Cart detail with book ID {} for user ID {} has been removed.", bookId, userId);
        } else {
            // Thông báo nếu không tìm thấy
            throw new IllegalArgumentException("Cart detail not found with book ID " + bookId + " for user ID " + userId);
        }
    }

}
