package com.exist.ecctraining.enums;

import lombok.Getter;

@Getter
public enum Status {
    NEW(1, "New"),
    ASSIGNED(2, "Assigned"),
    INPROGRESS(3, "In Progress"),
    CLOSED(0, "Closed");

    Status(Integer id, String title){
        this.id = id;
        this.title = title;
    }

    private Integer id;
    private String title;

}
