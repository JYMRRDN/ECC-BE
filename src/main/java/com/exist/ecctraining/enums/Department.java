package com.exist.ecctraining.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public enum Department {
    ADMIN(1, "Administrator"),
    IT(2, "Information Technology"),
    HR(3, "Human Resources"),
    SALES(4, "Sales");

    Department(Integer id, String departmentName) {
        this.id = id;
        this.departmentName = departmentName;
    }
    private Integer id;
    private String departmentName;

    @JsonCreator
    public static String getDepartmentName(Integer id) {
        for (Department d: values()) {
            if(d.id.equals(id)){
                return d.departmentName;
            }
        }
        return null;
    }
}
