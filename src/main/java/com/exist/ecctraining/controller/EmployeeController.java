package com.exist.ecctraining.controller;

import com.exist.ecctraining.model.Employee;
import com.exist.ecctraining.model.dto.EmployeeNosDTO;
import com.exist.ecctraining.service.impl.EmployeeServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/employee")
public class EmployeeController {

    private final EmployeeServiceImpl employeeService;

    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getEmployees(){
        return employeeService.getEmployees();
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Optional<Employee>> getEmployeeById(@PathVariable Long id){
        return employeeService.getEmployeeById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/update/{id}")
    public ResponseEntity<Employee> updateEmployeeById(@RequestBody Employee employee, @PathVariable Long id){
        return employeeService.updateEmployeeById(employee, id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable Long id){
        return employeeService.deleteEmployeeById(id);
    }

//    @PreAuthorize("hasRole('ADMIN')")
//    @PatchMapping("/assign/employee_to_ticket/{ticketId}")
//    public ResponseEntity<String> assignEmployeeToTicket(@PathVariable Long ticketId, @RequestParam("employeeNo") Long employeeNo) {
//        return employeeService.assignEmployeeToTicket(ticketId, employeeNo);
//    }
//
//    @PreAuthorize("hasRole('ADMIN')")
//    @PatchMapping("/assign/watchers_to_ticket/{ticketId}")
//    public ResponseEntity<String> assignWatchersToTicket(@PathVariable Long ticketId, @RequestBody EmployeeNosDTO employeeNosDto){
//        return employeeService.assignWatchersToTicket(ticketId, employeeNosDto);
//    }
}
