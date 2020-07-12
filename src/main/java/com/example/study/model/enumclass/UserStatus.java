package com.example.study.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor // enum 클래스는 , 기본적으로 모든 매개변수를 통해 생성되는 생성자를 생성해주어야 한다.
public enum UserStatus {
    // DB인덱스와 같은 id, 두번째는 Title, 세번째는 상세한 설명
    REGISTERED(0, "등록", "사용자 등록상태"),
    UNREGISTERED(1, "해지", "사용자 해지상태")
    ;

    private Integer id;
    private String title;
    private String description;
}
