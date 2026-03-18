package pl.projekt.psk.systemticketservicebackend.ticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.projekt.psk.systemticketservicebackend.ticket.model.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByTicket(Long ticketId);
}
