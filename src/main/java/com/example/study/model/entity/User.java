package com.example.study.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

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
    private String email;
    private String phoneNumber; // column 명은 phone_number, java에선 카멜로 표기
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
}
