package com.example.study.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderType {

    ALL(0,"묵음","모든 상품 일괄발송"),
    EACH(1, "개별", "모든 상품 개별발송")
    ;
    private Integer id;
    private String title;
    private String description;
}
