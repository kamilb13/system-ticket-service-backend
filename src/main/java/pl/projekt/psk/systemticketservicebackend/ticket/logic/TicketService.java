package pl.projekt.psk.systemticketservicebackend.ticket.logic;

import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;
import pl.projekt.psk.systemticketservicebackend.ticket.dto.TicketRequest;
import pl.projekt.psk.systemticketservicebackend.ticket.model.Ticket;
import pl.projekt.psk.systemticketservicebackend.ticket.model.TicketStatus;

import java.util.List;

@Slf4j
@Service
public class TicketService {

    public Ticket createTicketForClient(TicketRequest request, String name) {
        return null;
    }

    public @Nullable List<Ticket> getTicketsByUsername(String name) {
        return null;
    }

    public void addComment(Long ticketId, String content, String name) {
        return;
    }

    public List<Ticket> getAllTickets() {
        return null;
    }

    public void updateTicketStatus(Long ticketId, TicketStatus status) {

    }
}
