package com.example.demo.Repository;

import com.example.demo.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import org.springframework.web.bind.annotation.RequestParam;

@RepositoryRestResource(path = "book")
public interface BookRepository extends JpaRepository<Book, Integer> {
    Book findByBookId(Integer bookId);
    Page<Book> findByBookNameContaining(@RequestParam("tenSach") String tenSach , Pageable pageable);

    Page<Book> findByCategories_CategoryId
            (@RequestParam("maTheLoai") int maTheLoai,Pageable pageable);
    Page<Book> findByBookNameContainingAndCategories_CategoryId
            (@RequestParam("tenSach") String tenBook,@RequestParam("maTheLoai") Integer maTheLoai,Pageable pageable);
}
