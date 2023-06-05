package com.exist.ecctraining.controller;

import com.exist.ecctraining.model.Ticket;
import com.exist.ecctraining.model.dto.EmployeeNosDTO;
import com.exist.ecctraining.service.impl.TicketServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/ticket")
public class TicketController {

    private final TicketServiceImpl ticketService;

    public TicketController(TicketServiceImpl ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Ticket>> getTickets(){
        return ticketService.getTickets();
    }

    @GetMapping("/find/tickets_of_assignee/{id}")
    public ResponseEntity<List<Ticket>> getTicketsByEmployeeNo(@PathVariable Long id) {
        return ticketService.getTicketsByEmployeeNo(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket){
        return ticketService.createTicket(ticket);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/update/{id}")
    public ResponseEntity<Ticket> updateTicketById(@RequestBody Ticket ticket, @PathVariable Long id){
        return ticketService.updateTicketById(ticket, id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTicketById(@PathVariable Long id){
        return ticketService.deleteTicketById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/assign/employee_to_ticket/{ticketId}")
    public ResponseEntity<String> assignEmployeeToTicket(@PathVariable Long ticketId, @RequestParam("employeeNo") Long employeeNo) {
        return ticketService.assignEmployeeToTicket(ticketId, employeeNo);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/assign/watchers_to_ticket/{ticketId}")
    public ResponseEntity<String> assignWatchersToTicket(@PathVariable Long ticketId, @RequestBody EmployeeNosDTO employeeNosDto){
        return ticketService.assignWatchersToTicket(ticketId, employeeNosDto);
    }
}
