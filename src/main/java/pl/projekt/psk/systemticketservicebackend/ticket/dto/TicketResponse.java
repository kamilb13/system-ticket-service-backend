package pl.projekt.psk.systemticketservicebackend.ticket.dto;

import pl.projekt.psk.systemticketservicebackend.ticket.model.TicketStatus;

import java.time.LocalDateTime;

public record TicketResponse(
        Long id,
        String title,
        String ticketCreator,
        String description,
        String category,
        TicketStatus status,
        LocalDateTime createdAt
) {
}