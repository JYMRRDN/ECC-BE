package com.exist.ecctraining.enums;

import lombok.Getter;

@Getter
public enum Severity {
    LOW(1, "Low"),
    NORMAL(2, "Normal"),
    MAJOR(3, "Major"),
    CRITICAL(4, "Critical");

    Severity(Integer id, String title){
        this.id = id;
        this.title = title;
    }

    private Integer id;
    private String title;
}
