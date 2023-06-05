package com.exist.ecctraining.service;

import com.exist.ecctraining.model.Ticket;
import com.exist.ecctraining.model.dto.EmployeeNosDTO;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TicketService {

    ResponseEntity<Ticket> createTicket(Ticket ticket);

    ResponseEntity<List<Ticket>> getTickets();

    ResponseEntity<String> deleteTicketById(Long id);

    ResponseEntity<Ticket> updateTicketById(Ticket ticket, Long id);

    ResponseEntity<List<Ticket>> getTicketsByEmployeeNo(Long employeeNo);

    ResponseEntity<String> assignEmployeeToTicket(Long ticketId, Long employeeNo);

    ResponseEntity<String> assignWatchersToTicket(Long id, EmployeeNosDTO employeeNosDto);

}
