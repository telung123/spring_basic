package com.example.study.repository;


import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


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

        String account = "test3";
        String password = "qwer1234";
        String status = "REGISTERED";
        String email = "telung123@naver.com";
        String phoneNumber = "010-1234-3333";
        LocalDateTime registeredAt = LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";

        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setStatus(status);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setRegisteredAt(registeredAt);


        User newUser = userRepository.save(user);
        Assertions.assertNotNull(newUser);
        Assertions.assertEquals(newUser.getAccount(),account);
    }

    @Test
    @Transactional
    public void read(){ // Get method로 id 를 받음
        String phoneNumber = "010-1234-1234";
        User user = userRepository.findFirstByPhoneNumberOrderByIdDesc(phoneNumber);
        if ( user != null ) {
            user.getOrderGroupList().stream().forEach(orderGroup -> {
                System.out.println("주문묶음------------------");
                System.out.println("총금액 : " + orderGroup.getTotalPrice());
                System.out.println("수령인 : " + orderGroup.getRevAddress());
                System.out.println("수령인 : " + orderGroup.getRevName());
                System.out.println();
                System.out.println("주문상세------------------");
                System.out.println();
                orderGroup.getOrderDetailList().forEach(orderDetail -> {
                    System.out.println("파트너사 이름: " + orderDetail.getItem().getPartner().getName());
                    System.out.println("파트너사 카테고리 :" + orderDetail.getItem().getPartner().getCategory().getTitle());
                    System.out.println("주문상태 : "+ orderDetail.getStatus());
                    System.out.println("콜센터: "+orderDetail.getItem().getPartner().getCallCenter());
                    System.out.println("도착예정일자 : "+ orderDetail.getArrivalDate());
                    System.out.println("주문상품 : " + orderDetail.getItem().getName());
                });
            });
        }

        Assertions.assertNotNull(user);
        Assertions.assertEquals(user.getPhoneNumber(),phoneNumber);
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
