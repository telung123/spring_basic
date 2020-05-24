package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ItemRepositoryTest extends StudyApplicationTests {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void create(){
        Item item = new Item();
        item.setName("삼성노트북");
        item.setTitle("삼성노트북 A100");
        item.setStatus("UNREGISTERED");
        item.setPrice(100000);
//        item.setPartnerId(1L);


        Item newItem = itemRepository.save(item);
        assertNotNull(newItem);
    }

}
