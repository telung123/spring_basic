package com.example.study.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String status;

    private LocalDateTime orderAt;
    private LocalDateTime arrivalDate;
    private int quantity;
    private BigDecimal totalPrice;

    private LocalDateTime createdAt;
    private String createdBy;

    private LocalDateTime updatedAt;
    private String updatedBy;

    // orderDetail(자신) - N / user(상대) - 1
    @ManyToOne
    private Item item;

    @ManyToOne
    private OrderGroup orderGroup;
}
