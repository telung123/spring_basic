package com.example.study.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PartnerStatus {
    REGISTERED(0, "등록", "등록 파트너사"),
    UNREGISTERED(1, "미등록", "미등록 파트너사")
    ;

    private Integer id;
    private String title;
    private String description;
}
