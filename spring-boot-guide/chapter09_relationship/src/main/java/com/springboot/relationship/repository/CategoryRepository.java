package com.springboot.relationship.repository;

import com.springboot.relationship.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByCode(String code);
    boolean existsByCode(String code);
}
