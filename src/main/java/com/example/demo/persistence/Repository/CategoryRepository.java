package com.example.demo.persistence.Repository;
import com.example.demo.persistence.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "the-loai")
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
