package com.exist.ecctraining.service.impl;

import com.exist.ecctraining.advice.EntityAlreadyExistsException;
import com.exist.ecctraining.advice.EntityDeletionException;
import com.exist.ecctraining.advice.EntityNotFoundException;
import com.exist.ecctraining.enums.Status;
import com.exist.ecctraining.model.Employee;
import com.exist.ecctraining.model.Ticket;
import com.exist.ecctraining.model.dto.EmployeeNosDTO;
import com.exist.ecctraining.repository.EmployeeRepository;
import com.exist.ecctraining.repository.TicketRepository;
import com.exist.ecctraining.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final TicketRepository ticketRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, TicketRepository ticketRepository) {
        this.employeeRepository = employeeRepository;
        this.ticketRepository = ticketRepository;
    }

    @Override
    public ResponseEntity<List<Employee>> getEmployees() {
        List<Employee> list = employeeRepository.findAll();


        if (list.isEmpty()) {
            throw new EntityNotFoundException("There are no employees recorded in the system.");
        }
        return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<Employee> addEmployee(Employee employee) {
        Employee existingEmployee = employeeRepository.findByEmployeeNo(employee.getEmployeeNo());
        if(existingEmployee != null) {
            throw new EntityAlreadyExistsException(String.format("Employee with the employee number [%s] already exists.", employee.getEmployeeNo()));
        }
        return new ResponseEntity<>(employeeRepository.save(employee), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<Employee> updateEmployeeById(Employee employee, Long id) {
        Optional<Employee> existingEmployee = employeeRepository.findById(id);
        if(existingEmployee.isEmpty()) {
            throw new EntityNotFoundException(String.format("Employee with id [%d] not found.", id));
        }
        employee.setId(id);
        return new ResponseEntity<>(employeeRepository.save(employee), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<String> deleteEmployeeById(Long id) {
        Optional<Employee> existingEmployee = employeeRepository.findById(id);
        if(existingEmployee.isEmpty()) {
            throw new EntityNotFoundException(String.format("Employee with id [%d] not found.", id));
        }
        if(ticketRepository.findTicketByAssignee_Id(id) != null) {
            throw new EntityDeletionException(String.format("Employee deletion failed. Employee with id [%d] is currently assigned to a ticket.", id));
        }
        employeeRepository.deleteById(id);
        return new ResponseEntity<>(String.format("Employee with id [%d] deleted.",id), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<Optional<Employee>> getEmployeeById(Long id) {
        Optional<Employee> existingEmployee = employeeRepository.findById(id);
        if(existingEmployee.isEmpty()) {
            throw new EntityNotFoundException(String.format("Employee with id [%d] not found.", id));
        }
        return new ResponseEntity<>(existingEmployee, HttpStatus.OK);
    }

//    @Override
//    public ResponseEntity<String> assignEmployeeToTicket(Long id, Long employeeNo) {
//        Ticket existingTicket = ticketRepository.findTicketById(id);
//        if(existingTicket == null){
//            throw new EntityNotFoundException(String.format("Ticket with id [%d] does not exist.", id));
//        }
//        existingTicket.setAssignee(employeeRepository.findByEmployeeNo(employeeNo));
//        existingTicket.setStatus(Status.ASSIGNED);
//        ticketRepository.save(existingTicket);
//        return new ResponseEntity<>(String.format("Ticket [%d] assigned to Employee no. [%d]", id, employeeNo), HttpStatus.OK);
//    }
//
//    @Override
//    public ResponseEntity<String> assignWatchersToTicket(Long id, EmployeeNosDTO employeeNosDto) {
//        Ticket existingTicket = ticketRepository.findTicketById(id);
//        if(existingTicket == null){
//            throw new EntityNotFoundException(String.format("Ticket with id [%d] does not exist.", id));
//        }
//        List<Employee> watchers = employeeRepository.findEmployeeByEmployeeNoIn(employeeNosDto.getEmployeeNos());
//        existingTicket.setWatchers(watchers);
//        ticketRepository.save(existingTicket);
//        return new ResponseEntity<>(String.format("Watcher/s for ticket with id [%d] added successfully.", id), HttpStatus.OK);
//    }


}
