package com.exist.ecctraining.repository;

import com.exist.ecctraining.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findByEmployeeNo(Long employeeNo);

    List<Employee> findEmployeeByEmployeeNoIn(List<Long> employeeNos);
}
