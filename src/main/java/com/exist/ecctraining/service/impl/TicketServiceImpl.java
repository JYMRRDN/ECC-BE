package com.exist.ecctraining.service.impl;

import com.exist.ecctraining.advice.EntityDeletionException;
import com.exist.ecctraining.advice.EntityNotFoundException;
import com.exist.ecctraining.enums.Status;
import com.exist.ecctraining.model.Employee;
import com.exist.ecctraining.model.Ticket;
import com.exist.ecctraining.model.dto.EmployeeNosDTO;
import com.exist.ecctraining.repository.EmployeeRepository;
import com.exist.ecctraining.repository.TicketRepository;
import com.exist.ecctraining.service.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final EmployeeRepository employeeRepository;

    public TicketServiceImpl(TicketRepository ticketRepository, EmployeeRepository employeeRepository) {
        this.ticketRepository = ticketRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public ResponseEntity<List<Ticket>> getTickets() {
        return new ResponseEntity<>(ticketRepository.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Ticket>> getTicketsByEmployeeNo(Long id) {
        return new ResponseEntity<>(ticketRepository.getTicketsByAssignee_EmployeeNo(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Ticket> createTicket(Ticket ticket) {
        return new ResponseEntity<>(ticketRepository.save(ticket), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteTicketById(Long id) {
        Ticket existingTicket = ticketRepository.findTicketById(id);
        if(existingTicket == null) {
            throw new EntityNotFoundException(String.format("Ticket with id [%d] not found.", id));
        }
        if(existingTicket.getAssignee() != null){
            throw new EntityDeletionException(String.format("Ticket deletion failed. Ticket [%d] is currently assigned to employee number [%d]", id, existingTicket.getAssignee().getEmployeeNo()));
        }
        ticketRepository.deleteById(id);
        return new ResponseEntity<>(String.format("Ticket with id [%d] deleted.",id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Ticket> updateTicketById(Ticket ticket, Long id) {
        Optional<Ticket> existingTicket = ticketRepository.findById(id);
        if(existingTicket.isEmpty()) {
            throw new EntityNotFoundException(String.format("Ticket with id [%d] not found.", id));
        }
        ticket.setId(id);
        ticket.setAssignee(existingTicket.get().getAssignee());
        ticket.setWatchers(existingTicket.get().getWatchers());
        return new ResponseEntity<>(ticketRepository.save(ticket), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> assignEmployeeToTicket(Long id, Long employeeNo) {
        Ticket existingTicket = ticketRepository.findTicketById(id);
        if(existingTicket == null){
            throw new EntityNotFoundException(String.format("Ticket with id [%d] does not exist.", id));
        }
        existingTicket.setAssignee(employeeRepository.findByEmployeeNo(employeeNo));
        existingTicket.setStatus(Status.ASSIGNED);
        ticketRepository.save(existingTicket);
        return new ResponseEntity<>(String.format("Ticket [%d] assigned to Employee no. [%d]", id, employeeNo), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> assignWatchersToTicket(Long id, EmployeeNosDTO employeeNosDto) {
        Ticket existingTicket = ticketRepository.findTicketById(id);
        if(existingTicket == null){
            throw new EntityNotFoundException(String.format("Ticket with id [%d] does not exist.", id));
        }
        List<Employee> watchers = employeeRepository.findEmployeeByEmployeeNoIn(employeeNosDto.getEmployeeNos());
        existingTicket.setWatchers(watchers);
        ticketRepository.save(existingTicket);
        return new ResponseEntity<>(String.format("Watcher/s for ticket with id [%d] added successfully.", id), HttpStatus.OK);
    }

}
