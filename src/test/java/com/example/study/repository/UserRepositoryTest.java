package com.example.study.repository;


import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Item;
import com.example.study.model.entity.OrderDetail;
import com.example.study.model.entity.User;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;


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
        user.setAccount("tester");
        user.setEmail("tester@naver.com");
        user.setPhoneNumber("010-4545-4789");
        user.setCreatedAt(LocalDateTime.now()); // 현재시간
        user.setCreatedBy("Gabin");

        User newUser = userRepository.save(user); // 반환값 User (동일)
        System.out.println("newUser : " + newUser);
    }

    @Test
    @Transactional
    public void read(){ // Get method로 id 를 받음
        // findById Return : Optional의 Generic 으로 받게됨
        Optional<User> user = userRepository.findById(2L);

        // Optional 로 받아온 객체는 있을수도, 없을수도 있다고 가정.
        // 해서 'ifPresent' 로 객체가 있을 때만 값을 받아오게 처리
        user.ifPresent(selectUser ->{
            selectUser.getOrderDetailList().stream().forEach(order -> {
                Item item = order.getItem();
                System.out.println(item);
            });
//            System.out.println("user : " + item);
//            System.out.println("email : " + item.getEmail());
        });
    }

    @Test
    public void update(){
        Optional<User> user = userRepository.findById(2L);
        user.ifPresent(item->{
           item.setAccount("changeAcc");
           // Update 했기 때문에 변경시간/Author도 함께 변경
           item.setUpdatedAt(LocalDateTime.now());
           item.setUpdatedBy("Admin");

           // DB에 반영 (commit) (반환받은 user 객체를 save해줌)
           userRepository.save(item);
        });
    }

    @Test // 실제 RestAPI 에서 ex: DeleteMapping("/api/user")
    @Transactional
    public void delete(){
        Optional<User> user = userRepository.findById(1L);

        // 삭제 대상이 반드시 존재해야 함
        assertTrue(user.isPresent());

        user.ifPresent(item->{
            userRepository.delete(item); // delete - 반환없음
        });

        // 삭제 확인
        Optional<User> delUser = userRepository.findById(1L);
        assertFalse(delUser.isPresent());
    }
}
