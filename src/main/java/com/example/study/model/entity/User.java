package com.example.study.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity // == table
// @Table(name = "user") // class 명과 동일하기 때문에 생략
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 식별키 지정(Id), 식별키 전략 설정
    private long id;
    // @Column(name = "account") // 컬럼명과 동일하면 생략 (다를경우 용이)
    private String account;
    private String password;
    private String status;
    private String email;
    private String phoneNumber; // column 명은 phone_number, java에선 카멜로 표기

    private LocalDateTime registeredAt;
    private LocalDateTime unregisteredAt;

    private LocalDateTime createdAt;
    private String createdBy;

    private LocalDateTime updatedAt;
    private String updatedBy;

    // 1(자신) : N (OrderDetail)
    // 다수이기 때문에 단수 객체가 아닌 List<T> 로 설정
    // fetch type 설정,
    // mappedBy = 어떤 컬럼(변수) 에 mapping 할지 설정.
    // orderDetail 의 ManyToOne 으로 설정해준 변수와 동명이어야함.
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<OrderGroup> orderGroupList;
}
