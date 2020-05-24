package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

public class CategoryRepositoryTest extends StudyApplicationTests  {
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void create(){
        String type = "COMPUTER";
        String title = "컴퓨터";
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";

        Category category = new Category();
        category.setType(type);
        category.setTitle(title);

        // save - 반환 : 새로 들어간 Data
        Category newCategory = categoryRepository.save(category);

        // null 값이 아니어야 함을 의미
        Assertions.assertNotNull(newCategory);
        // 생성된 값이 동일해야 함
        Assertions.assertEquals(newCategory.getType(),type);
        Assertions.assertEquals(newCategory.getTitle(),title);

    }

    @Test
    public void read(){
        String type = "COMPUTER";
        Optional<Category> categoryOptional = categoryRepository.findByType(type);
        categoryOptional.ifPresent( c -> {
            // 동일한 카테고리인지 확인
            Assertions.assertEquals(c.getType(), type);
            System.out.println(c.getId());
            System.out.println(c.getTitle());
            System.out.println(c.getType());
        });

    }
}


