package pl.projekt.psk.systemticketservicebackend.ticket.mapper;

import pl.projekt.psk.systemticketservicebackend.ticket.dto.TicketResponse;
import pl.projekt.psk.systemticketservicebackend.ticket.model.Ticket;

public class TicketMapper {

    public static TicketResponse toTicketResponse(Ticket savedTicket) {
        return new TicketResponse(
                savedTicket.getId(),
                savedTicket.getTitle(),
                savedTicket.getUser().getUsername(),
                savedTicket.getDescription(),
                savedTicket.getCategory(),
                savedTicket.getStatus(),
                savedTicket.getCreatedAt(),
                CommentMapper.toCommentDto(savedTicket.getComments())
        );
    }
}
