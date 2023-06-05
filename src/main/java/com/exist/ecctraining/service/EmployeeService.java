package com.exist.ecctraining.service;

import com.exist.ecctraining.model.Employee;
import com.exist.ecctraining.model.dto.EmployeeNosDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    ResponseEntity<List<Employee>> getEmployees();
    ResponseEntity<Optional<Employee>> getEmployeeById(Long id);
    ResponseEntity<Employee> addEmployee(Employee employee);
    ResponseEntity<Employee> updateEmployeeById(Employee employee, Long id);
    ResponseEntity<String> deleteEmployeeById(Long id);
//    ResponseEntity<String> assignEmployeeToTicket(Long ticketId, Long employeeNo);
//    ResponseEntity<String> assignWatchersToTicket(Long id, EmployeeNosDTO employeeNosDto);
}
