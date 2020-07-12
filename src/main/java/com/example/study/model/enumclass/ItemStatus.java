package com.example.study.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ItemStatus {
    REGISTERED(0,"등록","등록된 아이템"),
    UNREGISTERED(1,"미등록","아이템 등록 안됨"),
    WAITING(2,"대기중", "아이템 등록 대기 상태")
    ;

    private Integer id;
    private String title;
    private String description;
}
