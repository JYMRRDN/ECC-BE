package com.exist.ecctraining.enums;

public enum Roles {
    ADMIN(0),
    USER(1);

    Roles(int code) {
        this.code = code;
    }
    private int code;
}
