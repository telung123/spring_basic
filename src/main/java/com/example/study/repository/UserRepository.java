package com.example.study.repository;

import com.example.study.model.entity.Category;
import com.example.study.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// 파일명 : Entity명 + Repository 로 보통 명명함.
// 첫번째 인자 : 어떤 타입의 클래스가 들어가는지 넣어줌
// 두번째 인자 : Primary key type (Long 인 이유 = user 의 id 멤버변수 형이 long이기 때문임)

// 이렇게 Repository 선언만으로 CRUD 수행이 가능해진다.
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // findFirstBy - 같은 번호로 여러명이 가입 가능
    // 가장 최근 1건 Return
    // ID 역순 핸드폰번호
    User findFirstByPhoneNumberOrderByIdDesc(String phoneNumber);

    // findByOOO - 조건. 아래는 account 로 찾음
    // select * from user where account = ? << test03, admin ...
//    Optional<User> findByAccount(String account);
//    Optional<User> findByEmail(String email);
}
