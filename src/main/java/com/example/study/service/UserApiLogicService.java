package com.example.study.service;

import com.example.study.model.entity.User;
import com.example.study.model.enumclass.UserStatus;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.UserApiRequest;
import com.example.study.model.network.response.UserApiResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserApiLogicService extends BaseService<UserApiRequest, UserApiResponse, User> {

    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {
        // 1. request data 가져오기
        UserApiRequest userApiRequest = request.getData();

        // 2. User 생성
        User user = User.builder()
                .account(userApiRequest.getAccount())
                .password(userApiRequest.getPassword())
                .status(UserStatus.REGISTERED)
                .phoneNumber(userApiRequest.getPhoneNumber())
                .email(userApiRequest.getEmail())
                .registeredAt(LocalDateTime.now())
                .build();

        User newUser = baseRepository.save(user);

        // 3. 생성된 Data를 기준으로 UserApiResponse 생성 후 Return
        return response(newUser);
    }

    @Override
    public Header<UserApiResponse> read(Long id) {
        // id 를 통해 -> repository getOne, getById 가져옴
        Optional<User> optional = baseRepository.findById(id);

        // User 를 받으면, userApiResponse return 함
        return optional
                .map(user-> response(user))
                .orElseGet(()->Header.ERROR("데이터 없음")); // 해당하는 user 없을 때

    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {
        // 1. data 가져옴
        UserApiRequest userApiRequest = request.getData();

        // 2. id로 user 찾음
        Optional<User> optional = baseRepository.findById(userApiRequest.getId());

        //3. update 시켜줌
        return optional.map(user-> {
            user.setAccount(userApiRequest.getAccount())
                .setPassword(userApiRequest.getPassword())
                .setEmail(userApiRequest.getEmail())
                .setStatus(userApiRequest.getStatus())
                .setPhoneNumber(userApiRequest.getPhoneNumber())
                .setRegisteredAt(userApiRequest.getRegisteredAt())
                .setUnregisteredAt(userApiRequest.getUnregisteredAt());
            return user;
        })
        .map(user -> baseRepository.save(user)) // update
        .map(user -> response(user)) // userApi response 생성
        .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        // 1. id로 user 찾기
        Optional<User> optional = baseRepository.findById(id);

        return optional.map(user->{
            baseRepository.delete(user);
            return Header.OK();
        })
        .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    private Header<UserApiResponse> response(User user) {
        UserApiResponse userApiResponse = UserApiResponse.builder()
                .id(user.getId())
                .account(user.getAccount())
                .password(user.getPassword())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .status(user.getStatus())
                .registeredAt(user.getRegisteredAt())
                .unregisteredAt(user.getUnregisteredAt())
                .build();
        return Header.OK(userApiResponse);
    }
}
