package com.exist.ecctraining.model;

import com.exist.ecctraining.enums.Severity;
import com.exist.ecctraining.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String description;
    private Severity severity;
    private Status status;
    @OneToOne
//    @JoinColumn(name = "employeeNo")
    private Employee assignee;
    @ManyToMany
    private List<Employee> watchers;
}
