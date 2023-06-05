package com.exist.ecctraining.model;

import com.exist.ecctraining.enums.Department;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.access.prepost.PostAuthorize;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "employee_no")
    private Long employeeNo;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "department")
    private Department department;



}
