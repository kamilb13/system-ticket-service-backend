package pl.projekt.psk.systemticketservicebackend.ticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.projekt.psk.systemticketservicebackend.ticket.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
