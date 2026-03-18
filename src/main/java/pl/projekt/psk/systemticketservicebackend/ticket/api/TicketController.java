package pl.projekt.psk.systemticketservicebackend.ticket.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.projekt.psk.systemticketservicebackend.ticket.dto.TicketRequest;
import pl.projekt.psk.systemticketservicebackend.ticket.logic.TicketService;
import pl.projekt.psk.systemticketservicebackend.ticket.model.Ticket;
import pl.projekt.psk.systemticketservicebackend.ticket.model.TicketStatus;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/tickets")
public class TicketController {

    private final TicketService ticketService;

    @PostMapping("/client")
    public ResponseEntity<Ticket> createTicket(@RequestBody TicketRequest request, Principal principal) {
        Ticket savedTicket = ticketService.createTicketForClient(request, principal.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTicket);
    }

    @GetMapping("/client")
    public ResponseEntity<List<Ticket>> getMyTickets(Principal principal) {
        return ResponseEntity.ok(ticketService.getTicketsByUsername(principal.getName()));
    }

    @PostMapping("/{ticketId}/comments")
    public ResponseEntity<Void> addComment(@PathVariable Long ticketId, @RequestBody String content, Principal principal) {
        ticketService.addComment(ticketId, content, principal.getName());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/tech")
    public ResponseEntity<List<Ticket>> getAllTickets() {
        return ResponseEntity.ok(ticketService.getAllTickets());
    }

    @PatchMapping("/tech/{ticketId}/status")
    public ResponseEntity<Void> updateStatus(
            @PathVariable Long ticketId,
            @RequestParam TicketStatus status) {

        ticketService.updateTicketStatus(ticketId, status);
        return ResponseEntity.noContent().build();
    }
}
