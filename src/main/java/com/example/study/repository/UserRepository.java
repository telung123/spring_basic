package com.example.study.repository;

import com.example.study.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// 파일명 : Entity명 + Repository 로 보통 명명함.
// 첫번째 인자 : 어떤 타입의 클래스가 들어가는지 넣어줌
// 두번째 인자 : Primary key type (Long 인 이유 = user 의 id 멤버변수 형이 long이기 때문임)

// 이렇게 Repository 선언만으로 CRUD 수행이 가능해진다.
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
