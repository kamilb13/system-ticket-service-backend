package pl.projekt.psk.systemticketservicebackend.ticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.projekt.psk.systemticketservicebackend.ticket.model.Ticket;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findAllByUserUsername(String username);
}
