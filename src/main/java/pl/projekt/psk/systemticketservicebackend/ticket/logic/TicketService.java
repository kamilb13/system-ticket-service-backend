package pl.projekt.psk.systemticketservicebackend.ticket.logic;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.projekt.psk.systemticketservicebackend.auth.UserRepository;
import pl.projekt.psk.systemticketservicebackend.auth.exceptions.UserNotFoundException;
import pl.projekt.psk.systemticketservicebackend.auth.model.User;
import pl.projekt.psk.systemticketservicebackend.ticket.dto.CommentDto;
import pl.projekt.psk.systemticketservicebackend.ticket.dto.TicketRequest;
import pl.projekt.psk.systemticketservicebackend.ticket.dto.TicketResponse;
import pl.projekt.psk.systemticketservicebackend.ticket.exceptions.TicketNotFoundException;
import pl.projekt.psk.systemticketservicebackend.ticket.mapper.TicketMapper;
import pl.projekt.psk.systemticketservicebackend.ticket.mapper.CommentMapper;
import pl.projekt.psk.systemticketservicebackend.ticket.model.Comment;
import pl.projekt.psk.systemticketservicebackend.ticket.model.Ticket;
import pl.projekt.psk.systemticketservicebackend.ticket.model.TicketStatus;
import pl.projekt.psk.systemticketservicebackend.ticket.repository.CommentRepository;
import pl.projekt.psk.systemticketservicebackend.ticket.repository.TicketRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public TicketResponse createTicketForClient(TicketRequest request, String username) {
        Optional<User> byUsername = userRepository.findByUsername(username);
        if (byUsername.isEmpty()) {
            log.error("User with username {} not found", username);
            throw new UserNotFoundException("User with username " + username + " not found");
        }
        Ticket savedTicket = ticketRepository.save(new Ticket(request.title(), byUsername.get(), request.description(), request.category()));
        return TicketMapper.toTicketResponse(savedTicket);
    }

    public List<TicketResponse> getTicketsByUsername(String username) {
        List<Ticket> allByUser = ticketRepository.findAllByUserUsername(username);
        return allByUser.stream()
                .map(TicketMapper::toTicketResponse)
                .toList();
    }

    public void addComment(Long ticketId, String content, String username) {
        Optional<Ticket> byId = ticketRepository.findById(ticketId);
        if (byId.isEmpty()) {
            log.error("Ticket with id {} not found", ticketId);
            throw new TicketNotFoundException("Ticket with id " + ticketId + " not found");
        }
        commentRepository.save(new Comment(byId.get(), content, username));
    }

    public List<TicketResponse> getAllTickets() {
        List<Ticket> all = ticketRepository.findAll();
        return all.stream()
                .map(TicketMapper::toTicketResponse)
                .toList();
    }

    public void updateTicketStatus(Long ticketId, TicketStatus status) {
        Optional<Ticket> byId = ticketRepository.findById(ticketId);
        if (byId.isPresent()) {
            Ticket ticket = byId.get();
            ticket.setStatus(status);
            ticketRepository.save(ticket);
        } else {
            log.error("Ticket with id {} not found", ticketId);
            throw new TicketNotFoundException("Ticket with id " + ticketId + " not found");
        }
    }

    public TicketResponse getTicketById(Long ticketId) {
        Optional<Ticket> byId = ticketRepository.findById(ticketId);
        if (byId.isEmpty()) {
            log.error("Ticket with id {} not found", ticketId);
            throw new TicketNotFoundException("Ticket with id " + ticketId + " not found");
        }
        return TicketMapper.toTicketResponse(byId.get());
    }

    public List<CommentDto> getCommentsByTicketId(Long ticketId) {
        return CommentMapper.toCommentDto(commentRepository.findAllByTicketId(ticketId));
    }
}
