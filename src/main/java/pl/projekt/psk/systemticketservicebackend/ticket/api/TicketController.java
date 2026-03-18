package pl.projekt.psk.systemticketservicebackend.ticket.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.projekt.psk.systemticketservicebackend.ticket.dto.AddCommentDto;
import pl.projekt.psk.systemticketservicebackend.ticket.dto.CommentDto;
import pl.projekt.psk.systemticketservicebackend.ticket.dto.TicketRequest;
import pl.projekt.psk.systemticketservicebackend.ticket.dto.TicketResponse;
import pl.projekt.psk.systemticketservicebackend.ticket.logic.TicketService;
import pl.projekt.psk.systemticketservicebackend.ticket.model.Comment;
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
    public ResponseEntity<TicketResponse> createTicket(@RequestBody TicketRequest request, Principal principal) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ticketService.createTicketForClient(request, principal.getName()));
    }

    @GetMapping("/client")
    public ResponseEntity<List<TicketResponse>> getMyTickets(Principal principal) {
        return ResponseEntity.ok(ticketService.getTicketsByUsername(principal.getName()));
    }

    @PostMapping("/comments")
    public ResponseEntity<Void> addComment(@RequestBody AddCommentDto commentRequest, Principal principal) {
        ticketService.addComment(commentRequest.ticketId(), commentRequest.comment(), principal.getName());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/tech")
    public ResponseEntity<List<TicketResponse>> getAllTickets() {
        return ResponseEntity.ok(ticketService.getAllTickets());
    }

    @PatchMapping("/tech/status")
    public ResponseEntity<Void> updateStatus(@RequestParam TicketStatus status, @RequestParam Long ticketId) {
        ticketService.updateTicketStatus(ticketId, status);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{ticketId}")
    public ResponseEntity<TicketResponse> getTicketById(@PathVariable Long ticketId) {
        return ResponseEntity.ok(ticketService.getTicketById(ticketId));
    }

    @GetMapping("/{ticketId}/comments")
    public ResponseEntity<List<CommentDto>> getCommentsByTicketId(@PathVariable Long ticketId) {
        return ResponseEntity.ok(ticketService.getCommentsByTicketId(ticketId));
    }
}
