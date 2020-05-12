package com.example.study.repository;


import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class UserRepositoryTest extends StudyApplicationTests {

    @Autowired
    private UserRepository userRepository;
    /* Autowired 의존성 주입. = DI (Dependency Injection)
        기존의 static 한 방식으로는
        private UserRepository userRepository = new UserRepository() 객체를 생성 후,
        userRepository.method() 등으로 객체를 사용 했음.
        DI(Dependency injection) 처리를 알아서 함.
        직접 객체를 생성하지 않고, 스프링에게 관리와 의존성 처리를 넘김
    * */

    @Test // Test 코드는 반드시 Test annotation 추가
    public void create(){
        /* 기존 SQL 방식으로 Data를 create 할때는 아래처럼 했음
        String sql = insert into user(%s, %s, %d) value(account,email...);
        JPA로는 Object 로 관리 하기 때문에 아래처럼 작성한다.

        DI 의 핵심은 싱글톤패턴이기 때문에 User 는 DI로 관리하지 않는다.
        * */
        User user = new User();
        //user.setId(); -- AutoIncrease 설정했기 때문에 따로 set 하지 않음
        user.setAccount("user03");
        user.setEmail("abc@gmail.com");
        user.setPhoneNumber("010-2333-2222");
        user.setCreatedAt(LocalDateTime.now()); // 현재시간
        user.setCreatedBy("Abc");

        User newUser = userRepository.save(user); // 반환값 User (동일)
        System.out.println("newUser : " + newUser);
    }
    public void read(){}
    public void update(){}
    public void delete(){}
}
